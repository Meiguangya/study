package watiDemo;

public class WatiNotifyAll implements Runnable {

    public static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new WatiNotifyAll(),"线程A");
        Thread threadB = new Thread(new WatiNotifyAll(),"线程B");

        Thread threadC = new Thread(()->{
            synchronized (obj){
                obj.notifyAll();
                System.out.println("notify all ...");
            }
        });

        threadB.start();
        threadA.start();

        Thread.sleep(1000);

        threadC.start();
    }


    @Override
    public void run() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "get obj");

            try {
                obj.wait();
                System.out.println(Thread.currentThread().getName() + "get obj again end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
