package stopTheThread;

public class StopTheThread2 {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            int num = 0;
            try{
                while(num<300 && !Thread.currentThread().isInterrupted()){
                    if(num%100==0){
                        System.out.println(num);
                    }
                    num++;
                }
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("over..");
        };

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }

}
