package com.cool.jedis;

import redis.clients.jedis.Jedis;

public class TestPing {

    static String host = "127.0.0.1";
    static int port = 6379;

    public static void main(String[] args) {
        //1.建立连接
        Jedis jedis = new Jedis(host,port);
        System.out.println(jedis.ping());
        //2.常用的api


    }

}
