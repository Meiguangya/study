package design_demo.singleton.count_demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author water33
 */
public class MyCount {

    private AtomicInteger count;

    private volatile static MyCount myCount;

    private MyCount() {
        count = new AtomicInteger(0);
    }

    public void addCount() {
        count.addAndGet(1);
    }

    //todo 一定要进行双重检查
    public static MyCount getInstance() {
        if (myCount == null) {
            synchronized (MyCount.class) {
                if(myCount==null){
                    myCount = new MyCount();
                }
            }
        }
        return myCount;
    }

    public int getCount() {
        return count.get();
    }

}
