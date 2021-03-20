package join;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class JoinInterrupt {

    public static void main(String[] args) {

        Thread threadMain = Thread.currentThread();

        Thread t1 = new Thread(()->{
            try {
                threadMain.interrupt();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
            e.printStackTrace();
        }

    }


}
