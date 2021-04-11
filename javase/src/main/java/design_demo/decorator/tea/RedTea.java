package design_demo.decorator.tea;

/**
 * @author water33
 */
public class RedTea extends Tea{

    public RedTea(){
        description = "红茶";
    }

    @Override
    public double cost() {
        return 2.5;
    }
}
