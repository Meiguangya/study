package com.cool;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;

/**
 * @author water33
 * 模拟生产者
 */
public class KafkaProducerTest {

    static Log log = LogFactory.get();

    public static void main(String[] args) {
        //创建用于连接kafka的properties
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        //当生产者发送消息到Kafka中，以什么策略进行返回
        props.setProperty("acks", "all");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //幂等性
        props.setProperty("enable.idempotence","true");

        //创建对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        //发送数据到topic中
        for (int i = 0; i < 100000; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("workMsg", null, i+"");

            kafkaProducer.send(record,(RecordMetadata recordMetadata, Exception exception)->{
                if(exception==null){
                    String topic = recordMetadata.topic();
                    int partition = recordMetadata.partition();
                    long offset = recordMetadata.offset();
                    log.info("topic:{},partition:{},offset:{}",topic,partition,offset);
                }else{
                    exception.printStackTrace();
                }
            });


//            Future<RecordMetadata> future = kafkaProducer.send(record);
//            try {
//                future.get();
//                log.info("第{}条消息发送成功", i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }

        //关闭
        kafkaProducer.close();
    }

}
