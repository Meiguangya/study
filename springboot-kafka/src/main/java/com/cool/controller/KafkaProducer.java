package com.cool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author water33
 */
@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/kafka/normal/{msg}")
    public String sendMsg1(@PathVariable("msg") String normalMsg){
        kafkaTemplate.send("topic1",normalMsg);
        return normalMsg;
    }

    @GetMapping("/kafka/callback/{msg}")
    public String sendMsg2(@PathVariable("msg") String callbackMsg){
        kafkaTemplate.send("topic1",callbackMsg).addCallback(
                success->{
                    // 消息发送到的topic
                    String topic = success.getRecordMetadata().topic();
                    // 消息发送到的分区
                    int partition = success.getRecordMetadata().partition();
                    // 消息在分区内的offset
                    long offset = success.getRecordMetadata().offset();
                    System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
                },
                fail->{
                    System.out.println("发送消息失败:" + fail.getMessage());
                });

        return callbackMsg;
    }

    @GetMapping("/kafka/callback2/{msg}")
    public String sendMsg3(@PathVariable("msg") String callbackMsg){
        kafkaTemplate.send("topic1",callbackMsg).addCallback(new ListenableFutureCallback<SendResult<String,String>>(){

            @Override
            public void onSuccess(SendResult<String, String> sendResult) {
                // 消息发送到的topic
                String topic = sendResult.getRecordMetadata().topic();
                // 消息发送到的分区
                int partition = sendResult.getRecordMetadata().partition();
                // 消息在分区内的offset
                long offset = sendResult.getRecordMetadata().offset();
                System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败:" + throwable.getMessage());
            }
        });
        return callbackMsg;
    }

    @GetMapping("/kafka/transaction/{msg}")
    @Transactional(transactionManager = "kafkaTransactionManager",rollbackFor = Exception.class)
    public String sendMsg4(@PathVariable("msg") String msg){
        kafkaTemplate.send("topic1", msg);
        kafkaTemplate.send("topic2",msg);
        return msg;
    }

}
