package join;

import java.util.concurrent.TimeUnit;

public class JoinDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t1.start();

        synchronized (t1){
            t1.wait();
        }

        System.out.println("over");
    }

}
