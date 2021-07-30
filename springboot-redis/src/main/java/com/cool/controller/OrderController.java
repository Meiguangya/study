package com.cool.controller;

import com.cool.zookeeper.ZKLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.KeeperException;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author water33
 */

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private RedisTemplate redisTemplate;

    public Lock lock = new ReentrantLock();

    @Autowired
    private Redisson redisson;

    CuratorFramework client = null;

    @PostConstruct
    public void initZookeeper() {
//        client = CuratorFrameworkFactory.newClient(ZK_ADDRESS,
//                new RetryNTimes(10, 5000));
//        client.start();
    }

    //zookeeper的地址
    private static final String ZK_ADDRESS = "127.0.0.1:2181";

    private static final String ZK_LOCK_PATH = "/zkLock";

    @RequestMapping("/create")
    public String createOrder(@RequestParam("id") Integer id) throws InterruptedException {
        String lock = "order_lock";

//        while (!redisTemplate.opsForValue().setIfAbsent("lock_" + id, -1,1, TimeUnit.SECONDS)) {
//
//        }
        RLock rLock = redisson.getLock(lock);
        rLock.lock(10, TimeUnit.SECONDS);

        int count = (Integer) redisTemplate.opsForValue().get("order_" + id);
        try {
            if (count > 0) {
                int realCount = count - 1;
                redisTemplate.opsForValue().set("order_" + id, realCount);
                System.out.println("购买商品成功，剩余库存" + realCount);
                //redisTemplate.delete("lock_" + id);
                return "success";
            } else {
                System.out.println("库存不足");
                //redisTemplate.delete("lock_" + id);
                return "fail";
            }
        } finally {
            rLock.unlock();
        }

    }


    @RequestMapping("/create_by_zk")
    public String createOrderByZk(@RequestParam("id") Integer id) throws InterruptedException {
        try {
            synchronized (this) {
                ZKLock zkLock = new ZKLock();
                zkLock.acquireLock();
                int count = (Integer) redisTemplate.opsForValue().get("order_" + id);
                if (count > 0) {
                    int realCount = count - 1;
                    redisTemplate.opsForValue().set("order_" + id, realCount);
                    System.out.println("购买商品成功，剩余库存" + realCount);
                    try {
                        zkLock.unlock();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    }
                    return "success";
                } else {
                    System.out.println("库存不足");
                    try {
                        zkLock.unlock();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    }
                    return "fail";
                }
            }
        } finally {

        }
    }


    @RequestMapping("/create_by_curator")
    public String createOrderByCurator(@RequestParam("id") Integer id) throws InterruptedException {
        String result="fail";
        synchronized (this) {
            InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
            try{
                if(lock.acquire(6,TimeUnit.SECONDS)){
                    int count = (Integer) redisTemplate.opsForValue().get("order_" + id);
                    if (count > 0) {
                        int realCount = count - 1;
                        redisTemplate.opsForValue().set("order_" + id, realCount);
                        System.out.println("购买商品成功，剩余库存" + realCount);
                        result = "success";
                    } else {
                        System.out.println("库存不足");
                        result = "fail";
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    System.out.println("锁释放异常");
                }
            }

        }

        return result;
    }

}
