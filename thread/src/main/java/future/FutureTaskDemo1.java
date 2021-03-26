package future;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.concurrent.*;

/**
 * @author water33
 */
@SuppressWarnings("all")
public class FutureTaskDemo1 {

    static Log log = LogFactory.get();

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("current thread:{}", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            return "over";
        });
        log.info("main...");
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(futureTask);
        try {
            String result = futureTask.get();
            log.info("result:{}", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
    }

}
