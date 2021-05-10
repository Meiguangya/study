package threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author water33
 */
public class ThreadPoolDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executors.newFixedThreadPool(10);

        FutureTask task = new FutureTask(new CallTest());
        task.run();
        Object o = task.get();
        System.out.println(o);
    }

    static class CallTest implements Callable{
        @Override
        public Object call() throws Exception {
            System.out.println("call...");
            return null;
        }
    }

}
