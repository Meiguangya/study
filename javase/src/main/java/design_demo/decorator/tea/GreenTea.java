package design_demo.decorator.tea;

/**
 * @author water33
 */
public class GreenTea extends Tea {

    public GreenTea() {
        description = "绿茶";
    }

    @Override
    public double cost() {
        return 3.5;
    }

}
