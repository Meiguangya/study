package producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("all")
public class ProAndConDemo2 {

    private static Lock reentrantLock = new ReentrantLock();
    private static Condition full = reentrantLock.newCondition();
    private static Condition empty = reentrantLock.newCondition();

    public static void main(String[] args) {
        List<Integer> storge = new ArrayList<>();

        new Thread(new Consumer(reentrantLock, storge)).start();

        new Thread(new Producer(reentrantLock, storge, 20)).start();

    }


    static class Producer implements Runnable {

        private Lock lock;
        private List<Integer> storge;
        private int maxSize;

        public Producer(Lock lock, List<Integer> storge, int maxSize) {
            this.lock = lock;
            this.storge = storge;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (storge.size() == maxSize) {
                        System.out.println("队列满了，生产者停止生产...");
                        full.await();
                    }
                    //Thread.sleep(1000);
                    Integer i = new Random().nextInt();
                    System.out.println("生产者生产了" + i);
                    storge.add(i);
                    empty.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }


    static class Consumer implements Runnable {
        private Lock lock;
        private List<Integer> storge;

        public Consumer(Lock lock, List<Integer> storge) {
            this.lock = lock;
            this.storge = storge;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (storge.isEmpty()) {
                        System.out.println("队列为空，消费者开始等待");
                        empty.await();
                    }

                    Thread.sleep(1000);
                    Integer i = storge.remove(0);
                    System.out.println("消费者消费了" + i);
                    full.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}


