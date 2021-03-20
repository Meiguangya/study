package watiDemo;

@SuppressWarnings("all")
public class PrintDemo3 {
    private static int count = 0;
    private static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            while(count<=100){
                synchronized (lock){
                    if (count % 2 != 0) {
                        System.out.println("jishu:" + count++);
                        lock.notify();
                        if(count<=100){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while(count<=100){
                synchronized (lock){
                    if (count % 2 == 0) {
                        System.out.println("oshu:" + count++);
                        lock.notify();
                        if(count<=100){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();

    }


}
