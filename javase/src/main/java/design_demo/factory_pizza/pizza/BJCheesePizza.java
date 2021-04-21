package design_demo.factory_pizza.pizza;

/**
 * @author water33
 */
public class BJCheesePizza extends CheesePizza{

    public BJCheesePizza(){
        name = "北京的奶酪披萨";
    }

    @Override
    public void prepare() {
        System.out.println(name+"prepare...");
    }
}
