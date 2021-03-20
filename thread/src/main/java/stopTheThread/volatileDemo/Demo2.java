package stopTheThread.volatileDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Demo2 {

    public static void main(String[] args) throws InterruptedException {

        Demo2 body = new Demo2();

        BlockingQueue store = new ArrayBlockingQueue(2);
        Producer producer = body.new Producer(store);

        Thread produceThread = new Thread(producer);
        produceThread.start();
        Thread.sleep(5000);

        Consumer consumer = body.new Consumer(store);
        while(consumer.needMoreNums()){
            System.out.println(consumer.store.take()+"被消费");
            Thread.sleep(2000);
        }
        System.out.println("消费者不需要数据了");

        //停止生产
        //producer.cancel = true;
        produceThread.interrupt();

    }



    class Producer implements Runnable {
        volatile boolean cancel = false;
        BlockingQueue store;

        public Producer(BlockingQueue store) {
            this.store = store;
        }

        @Override
        public void run() {
            int num = 1;
            try {
                while (num < 20000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println("生产者生产"+num);
                        store.put(num);
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("生产者被中断了");
                e.printStackTrace();
            }
            System.out.println("生产者 over..");
        }
    }

    class Consumer {
        BlockingQueue store;

        public Consumer(BlockingQueue sotre) {
            this.store = sotre;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.8) {
                return false;
            }
            return true;
        }

    }

}


