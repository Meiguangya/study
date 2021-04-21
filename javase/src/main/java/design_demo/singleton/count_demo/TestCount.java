package design_demo.singleton.count_demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author water33
 */
public class TestCount {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(10000);

        for (int i = 0; i < 10000; i++) {
            exec.execute(() -> {

                MyCount myCount = MyCount.getInstance();
                myCount.addCount();
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        exec.shutdown();

        System.out.println(MyCount.getInstance().getCount());
    }

}
