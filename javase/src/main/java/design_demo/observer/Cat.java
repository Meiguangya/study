package design_demo.observer;

/**
 * @author water33
 */
public class Cat implements Animal{

    @Override
    public void masterHome(String food) {
        if("鱼".equals(food))
            System.out.println("欢迎主任回家，cat~~,我想吃"+food);
    }

}
