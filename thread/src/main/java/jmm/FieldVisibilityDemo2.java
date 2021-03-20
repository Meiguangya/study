package jmm;

@SuppressWarnings("all")
public class FieldVisibilityDemo2 {

    volatile int x = 0;

    public static void main(String[] args) {

        while(true){
            FieldVisibilityDemo2 demo2 = new FieldVisibilityDemo2();
            new Thread(()->{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo2.change();
            }).start();

            new Thread(()->{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo2.print();
            }).start();

        }
    }

    private void print() {
        int b = x;
        System.out.println(b);
    }

    private void change() {
        x = 1;
    }

}
