package design_demo.singleton.count_demo;

/**
 * @author water33
 */
public class TestMyBook {

    public static void main(String[] args) {
        MyBook myBook1 = MyBook.getInstance();
        MyBook myBook2 = MyBook.getInstance();
        MyBook myBook3 = MyBook.getInstance();

        System.out.println(myBook1);
        System.out.println(myBook2);
        System.out.println(myBook3);
    }

}
