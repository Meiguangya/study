package design_demo.singleton.count_demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author water33
 */
@SuppressWarnings("all")
public class MyBook {

    private AtomicInteger wantLookNums;

    public void wantToLook() {
        wantLookNums.addAndGet(1);
    }

    private MyBook() {
        wantLookNums = new AtomicInteger(0);
    }

    public static MyBook getInstance() {
        return SingletonBook.INSTANCE.getMyBook();
    }

    private enum SingletonBook {
        INSTANCE;

        private MyBook myBook;

        //构造方法
        SingletonBook() {
            myBook = new MyBook();
        }

        public MyBook getMyBook() {
            return myBook;
        }

        public static void test() {
            SingletonBook[] values = values();
            System.out.println(values.length);
            System.out.println(values[0]);
            System.out.println(values[1]);

            SingletonBook twoin = valueOf("TWOIN");
            System.out.println("--" + twoin);
        }
    }

    public static void main(String[] args) {
        SingletonBook.test();
    }

}
