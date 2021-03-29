package com.cool;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @author water33
 */
@SuppressWarnings("all")
public class KafkaConsumerTest {

    static Log log = LogFactory.get();

    public static void main(String[] args) {
        log.info("try to consumer...");
        //创建用于连接kafka的properties
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test"); //消费组名称
        props.setProperty("enable.auto.commit", "true"); //自动提交offset
        props.setProperty("auto.commit.interval.ms", "1000"); //自动提交offset的时间间隔
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        //创建consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //指定消费者的topic
        consumer.subscribe(Arrays.asList("workMsg"));

        //while循环不断拉取数据
        while (true) {
            //5秒超时 一次拉去一批数据
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(5));

            for (ConsumerRecord<String, String> record : consumerRecords) {
                String topic = record.topic();
                int partition = record.partition();
                long offset = record.offset();
                String key = record.key();
                String value = record.value();
                log.info("topic:{},partition:{},offset:{},key:{},value:{}",topic,partition,offset,key,value);
            }
        }


    }
}
