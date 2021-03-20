package stopTheThread;

public class StopTheThread1 implements Runnable {

    public void run() {
        int num = 0;
        //没有中断就继续打印
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num);
            }
            num++;
        }
        System.out.println("over...");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StopTheThread1());
        t.start();
        Thread.sleep(1000);

        //使用interrupt 通知线程中断
        t.interrupt();


    }

}
