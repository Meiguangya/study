package watiDemo;

import java.util.Date;
import java.util.LinkedList;

public class ProducerConsumerModel {


    public static void main(String[] args) {
        ProducerConsumerModel model = new ProducerConsumerModel();
        EventStorage storage = model.new EventStorage(10);
        Producer producer = model.new Producer(storage);
        Consumer consumer = model.new Consumer(storage);

        new Thread(producer).start();
        new Thread(consumer).start();
    }


    class Producer implements Runnable {

        private EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.put();
            }
        }
    }


    class Consumer implements Runnable {

        private EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.take();
            }
        }
    }

    class EventStorage {
        private int maxSize;
        private LinkedList<Date> storage;

        public EventStorage(int maxSize) {
            this.maxSize = maxSize;
            this.storage = new LinkedList<Date>();
        }

        public synchronized void put() {
            while (storage.size() == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(new Date());
            System.out.println("仓库里有了" + storage.size() + "个产品");
            notify();
        }

        public synchronized void take() {
            while (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了" + storage.poll() + ",仓库还剩下" + storage.size());
            notify();
        }

    }


}


