package com.cool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

/**
 * @author water33
 */
//@Configuration
public class KafkaConfiguration {


//    /**
//     * Producer 工厂配置
//     */
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        DefaultKafkaProducerFactory<String, String> defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(null);
//        defaultKafkaProducerFactory.setTransactionIdPrefix("tx-");
//        return defaultKafkaProducerFactory;
//    }

//    @Bean
//    public KafkaTransactionManager kafkaTransactionManager(ProducerFactory producerFactory) {
//        return new KafkaTransactionManager<>(producerFactory);
//    }


}
