package com.cool.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {
    static String host = "127.0.0.1";
    static int port = 6379;

    public static void main(String[] args) {
        Jedis jedis = new Jedis(host, port);

        JSONObject obj1 = new JSONObject();
        obj1.put("name", "小红");
        obj1.put("age", 10);

        JSONObject obj2 = new JSONObject();
        obj2.put("name", "小黄");
        obj2.put("age", 12);

        //1.开启事务
        Transaction multi = jedis.multi();
        try {
            //2.命令入队
            multi.set("user1", obj1.toJSONString());
            multi.set("user2", obj2.toJSONString());
            //3.执行事务
            multi.exec();
        } catch (Exception e) {
            //4.发生错误
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }


    }

}
