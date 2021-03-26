package future;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.concurrent.*;
/**
 * @author water33
 */
public class FutureDemo1 {

    static Log log = LogFactory.get();

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("MyCallable...");
            TimeUnit.SECONDS.sleep(5);
            return "over..";
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        Future<String> future = exec.submit(new MyCallable());
        log.info("main..");

        try {
            String result = future.get();
            log.info("result:{}",result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            exec.shutdown();
        }

    }

}
