package com.cool.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.util.concurrent.TimeUnit;

/**
 * @author water33
 */
public class MainTest {

    private void sell(int i) throws InterruptedException {
        System.out.println("卖票开始");

        TimeUnit.SECONDS.sleep(2);

        System.out.println("卖票结束"+i);

    }

    public void sellWithLock(int i) throws InterruptedException, KeeperException {
        Lock lock = new Lock();
        lock.acquireLock();
        sell(i);
        lock.unlock();
    }


    public static void main(String[] args) throws InterruptedException, KeeperException {
        MainTest mainTest = new MainTest();
        for (int i = 0; i < 10; i++) {
            mainTest.sellWithLock(i);
        }
    }

}
