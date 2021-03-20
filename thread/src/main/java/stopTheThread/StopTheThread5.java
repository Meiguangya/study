package stopTheThread;

public class StopTheThread5 implements Runnable {

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                toSleep();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    //抛出异常签名 让run方法自己中断
    private void toSleep() throws InterruptedException {
        Thread.sleep(1000);
    }


    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new StopTheThread5());

        t.start();
        Thread.sleep(1000);
        t.interrupt();

    }

}
