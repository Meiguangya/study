package watiDemo;

@SuppressWarnings("all")
public class PrintDemo2 {

    private static int count = 0;
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if (count % 2 != 0) {
                        System.out.println("jishu:" + count++);
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if (count % 2 == 0) {
                        System.out.println("oshu:" + count++);
                    }
                }
            }
        });

        t1.start();
        t2.start();

    }

}
