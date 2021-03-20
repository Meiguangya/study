package watiDemo;

/**
 * 两个线程交替打印0～100的奇偶数
 */
public class PrintDemo {

    private static Object obj = new Object();

    public static void main(String[] args) {

        Thread a = new Thread(new ThreadA(obj));
        Thread b = new Thread(new ThreadB(obj));

        a.start();
        b.start();
    }
}

class ThreadB implements Runnable {
    private Object obj;

    public ThreadB(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 0; i < 100; i++) {
                if ((i & 1) == 1) {
                    System.out.println("基数：" + i);
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        }
    }
}


class ThreadA implements Runnable {
    private Object obj;

    public ThreadA(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 0; i < 100; i++) {
                if ((i & 1) == 0) {
                    System.out.println("偶数：" + i);
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        }
    }
}
