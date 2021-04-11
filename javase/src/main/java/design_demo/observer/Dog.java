package design_demo.observer;

/**
 * @author water33
 */
public class Dog implements Animal{
    @Override
    public void masterHome(String food) {
        if("骨头".equals(food))
            System.out.println("欢迎主人回家,dog～～,我想吃"+food);
    }
}
