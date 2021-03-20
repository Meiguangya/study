package watiDemo;

/**
 * wati和notify的基本用法
 */
public class Wati {

    public static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();
        t1.start();
        Thread t2 = new Thread2();

        Thread.sleep(1000);
        t2.start();
    }





    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("线程1开始执行");
                try {
                    //调用wait方法 会释放掉锁
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1获取到了锁");

            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("线程2开始执行");
                //唤醒其他线程，等同步代码块执行完后 释放掉锁
                obj.notify();
                System.out.println("线程2调用notify");
            }
        }
    }




}
