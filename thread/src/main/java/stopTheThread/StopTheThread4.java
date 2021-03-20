package stopTheThread;

public class StopTheThread4 implements Runnable {

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");

            toSleep();
        }
    }

    //传递中断
    private void toSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new StopTheThread4());

        t.start();
        Thread.sleep(1000);
        t.interrupt();

    }

}
