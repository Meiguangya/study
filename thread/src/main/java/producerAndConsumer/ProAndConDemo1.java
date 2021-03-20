package producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProAndConDemo1 {
    private static List<Integer> storge = new ArrayList<>();

    public static void main(String[] args) {
        ProAndConDemo1 proAndConDemo1 = new ProAndConDemo1();

        Producer producer = new Producer(10, storge);
        Consumer consumer = new Consumer(storge);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        Executor exec = Executors.newCachedThreadPool();

        ExecutorService service = Executors.newFixedThreadPool(15);

        for (int i = 0; i < 2000; i++) {
            service.submit(producer);
            service.submit(consumer);
        }
        service.shutdown();
    }

}

class Producer implements Runnable {

    private List<Integer> storge;
    private int max;

    public Producer(int max, List<Integer> storge) {
        this.max = max;
        this.storge = storge;
    }


    @Override
    public void run() {

        synchronized (storge) {
            while (storge.size() == max) {
                System.out.println("仓库已满，生产者wait...");
                try {
                    storge.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer i = new Random().nextInt();
            storge.add(i);
            System.out.println("生产者生产" + i);
            storge.notifyAll();
        }

    }
}

class Consumer implements Runnable {

    private List<Integer> storge;

    public Consumer(List<Integer> storge) {
        this.storge = storge;
    }

    @Override
    public void run() {
        synchronized (storge) {
            while (storge.size() == 0) {
                System.out.println("没有数据消费,消费者wait...");
                try {
                    storge.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer i = storge.remove(0);
            System.out.println("消费者消费了:" + i);
            storge.notifyAll();
        }
    }
}


