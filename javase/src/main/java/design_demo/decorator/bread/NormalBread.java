package design_demo.decorator.bread;

/**
 * @author water33
 */
public class NormalBread extends Bread{
    @Override
    public String getDescription() {
        return "普通面包";
    }

    @Override
    public double cost() {
        return 1.0;
    }
}
