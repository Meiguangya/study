package producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@SuppressWarnings("all")
public class ProAndConDemo3 {

    public static BlockingQueue<Integer> storgeQ = new ArrayBlockingQueue(20);

    public static void main(String[] args) {
        List<Integer> storge = new ArrayList<>();
        new Thread(new Consumer(storgeQ)).start();
        new Thread(new Producer(storgeQ)).start();

    }


    static class Producer implements Runnable {
        public BlockingQueue<Integer> queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Integer i = new Random().nextInt();
                    System.out.println("生产者生产了"+i);
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class Consumer implements Runnable {
        public BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("消费者消费了"+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


