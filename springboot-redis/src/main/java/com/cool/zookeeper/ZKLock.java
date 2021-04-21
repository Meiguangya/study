package com.cool.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author water33
 */
public class ZKLock {

    String connectString = "127.0.0.1:2181";
    String path;
    private static final String LOCK_ROOT_PATH = "/Locks";
    private static final String LOCK_NODE_PATH = "lock_";
    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZKLock() {
        //String connectString, int sessionTimeout, Watcher watcher
        try {
            zooKeeper = new ZooKeeper(connectString, 5000, (WatchedEvent event) -> {
                if (event.getType() == Watcher.Event.EventType.None) {
                    if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                        //System.out.println("连接成功");
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //获取锁
    public void acquireLock() {
        //创建锁节点
        createLock();
        //尝试获取锁
        tryLock();
    }

    Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDeleted) {
                synchronized (this) {
                    this.notifyAll();
                }
            }
        }
    };

    private void tryLock() {
        try {
            //获取所有的锁节点
            List<String> locks = zooKeeper.getChildren(LOCK_ROOT_PATH, false);

            //排序
            Collections.sort(locks);

            int index = locks.indexOf(path.substring(LOCK_ROOT_PATH.length() + 1));
            if (index == 0) {
                //System.out.println("成功获取锁");
                return;
            } else {
                String prePath = locks.get(index - 1);
                //监控上一个节点
                Stat stat = zooKeeper.exists(LOCK_ROOT_PATH + "/" + prePath, watcher);
                if (stat == null) {
                    tryLock();
                }
                //使用监听器同步阻塞住
                synchronized (watcher) {
                    watcher.wait();
                }
                tryLock();
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createLock() {
        try {
            //判断LOCK根节点是否存在
            Stat stat = zooKeeper.exists(LOCK_ROOT_PATH, false);
            if (stat == null) {
                zooKeeper.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
            }

            //创建lock节点
            path = zooKeeper.create(LOCK_ROOT_PATH + "/" + LOCK_NODE_PATH, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            //System.out.println("创建节点" + path + "成功");

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock() throws KeeperException, InterruptedException {
        zooKeeper.delete(this.path,-1);
        zooKeeper.close();
        //System.out.println("锁已释放"+this.path);
    }


    public static void main(String[] args) {
        ZKLock lock = new ZKLock();
        lock.createLock();
    }

}
