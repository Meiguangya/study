package design_demo.factory_pizza.pizza;

/**
 * @author water33
 */
public class CheesePizza extends Pizza{

    public CheesePizza(){
        name = "奶酪披萨";
    }

    @Override
    public void prepare() {
        System.out.println(name+"准备原材料");
    }


}
