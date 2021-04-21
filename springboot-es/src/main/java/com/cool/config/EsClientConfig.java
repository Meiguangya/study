package com.cool.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author water33
 */
@Configuration
public class EsClientConfig {

    /**
     * RestHighLevelClient client = new RestHighLevelClient(
     RestClient.builder(
     new HttpHost("localhost", 9200, "http"),
     new HttpHost("localhost", 9201, "http")));
     */

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")));

        return client;
    }

}
