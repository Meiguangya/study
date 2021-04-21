package design_demo.factory_pizza.pizza;

/**
 * @author water33
 */
public class GreekPizza extends Pizza{

    public GreekPizza(){
        name = "希腊披萨";
    }

    @Override
    public void prepare() {
        System.out.println(getName()+"准备原材料");
    }


}
