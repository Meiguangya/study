package thread_local_demo.demo1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author water33
 */
public class ThreadLocalDemo01 {

    public static ThreadLocal<SimpleDateFormat> sfThreadLocal =
            ThreadLocal.withInitial(()->new SimpleDateFormat("mm:ss"));

    public static void main(String[] args) {
        String result = new ThreadLocalDemo01().serviceMethod();
        System.out.println("------------"+result);
    }

    public void theadMethod(){
        ExecutorService exec = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 1000; i++) {
            final int seconds = i;
            exec.execute(()->{
                System.out.println(date(seconds));
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }

    public String serviceMethod(){
        theadMethod();
        return "success";
    }
    
    public static String date(int seconds){
        Date date = new Date(1000*seconds);
        SimpleDateFormat sf = sfThreadLocal.get();
        String result = sf.format(date);
        return result;
    }

}
