package design_demo.factory_pizza.pizza;

/**
 * @author water33
 */
public class BjGreekPizza extends Pizza{

    public BjGreekPizza(){
        name = "北京希腊披萨";
    }

    @Override
    public void prepare() {
        System.out.println(name+"prepare...");
    }
}
