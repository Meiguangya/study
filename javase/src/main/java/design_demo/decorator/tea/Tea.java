package design_demo.decorator.tea;

/**
 * @author water33
 */
public abstract class Tea {

    public String description = "茶";

    public abstract double cost();

    public String getDescription(){
        return description;
    }

}
