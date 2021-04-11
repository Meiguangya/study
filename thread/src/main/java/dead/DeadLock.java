package dead;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author water33
 */
public class DeadLock{

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        DeadThread1 thread1 = new DeadThread1(o1,o2);
        DeadThread2 thread2 = new DeadThread2(o1,o2);

        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.submit(thread1);
        exec.submit(thread2);



    }

}

class DeadThread1 implements Callable{

    private final Object o1;
    private final Object o2;
    public DeadThread1(Object o1,Object o2){
        this.o1=o1;
        this.o2=o2;
    }

    @Override
    public Object call() throws Exception {
        synchronized (o1){
            System.out.println("DeadThread1... start");
            TimeUnit.MILLISECONDS.sleep(100);
            synchronized (o2){
                System.out.println("DeadThread1..lock o2");
            }
            System.out.println("DeadThread1... end");
        }
        return null;
    }
}


class DeadThread2 implements Callable{

    private final Object o1;
    private final Object o2;
    public DeadThread2(Object o1,Object o2){
        this.o1=o1;
        this.o2=o2;
    }

    @Override
    public Object call() throws Exception {
        synchronized (o2){
            System.out.println("DeadThread2... start");
            TimeUnit.MILLISECONDS.sleep(100);
            synchronized (o1){
                System.out.println("DeadThread2..lock o1");
            }
            System.out.println("DeadThread2... end");
        }
        return null;
    }
}


