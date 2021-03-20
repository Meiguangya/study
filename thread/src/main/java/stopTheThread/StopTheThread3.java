package stopTheThread;

public class StopTheThread3 {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            int num = 0;
            try{
                while(num<10000){
                    if(num%100==0){
                        System.out.println(num);
                    }
                    num++;
                    Thread.sleep(10);
                }
            }catch (InterruptedException e){
                System.out.println("e....");
                e.printStackTrace();
            }
            System.out.println("over..");
        };

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(5000);
        t.interrupt();
    }

}
