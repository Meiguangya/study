package design_demo.decorator.bread;

/**
 * @author water33
 */
public class MeatsBread extends Bread{


    @Override
    public String getDescription() {
        return "肉松面包";
    }

    @Override
    public double cost() {
        return 2.0;
    }
}
