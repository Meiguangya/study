package thread_local_demo.demo3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author water33
 * ThreadLocal 用作保存每个线程独享的对象，
 * 为每个线程都创建一个副本，
 * 这样每个线程都可以修改自己所拥有的副本, 而不会影响其他线程的副本，确保了线程安全
 */
public class ThreadLocalDemo3 {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newFixedThreadPool(10);
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 1000; i++) {
            exec.submit(() -> {
                strThreadLocal.set(Thread.currentThread().getName());
                String str = strThreadLocal.get();
                concurrentHashMap.put(str, str);
                strThreadLocal.remove();
            });
        }

        exec.shutdown();

        concurrentHashMap.forEach((k, v) -> {
            System.out.println(k + "---" + v);
        });

    }

    private static ThreadLocal<String> strThreadLocal = new ThreadLocal<>();

}
