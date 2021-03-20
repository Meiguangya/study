package jmm;

@SuppressWarnings("all")
public class FieldVisibility {
    int a = 1;
    int b = 2;

    private void print() {
        System.out.println("b:" + b + ",a=" + a);
    }

    private void change() {
        a = 3;
        b = a;
    }


    // a=3,b=3
    // a=1,b=2
    // a=3,b=2
    // b=3,a=1
    public static void main(String[] args) {
        int i = 0;
        while(true){
            i++;
            FieldVisibility demo = new FieldVisibility();

            Thread t1 = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo.change();
            });

            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo.print();
            });

            t1.start();
            t2.start();
        }
    }


}
