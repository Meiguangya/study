package design_demo.factory_pizza.pizza;

/**
 * @author water33
 */
public class LDCheesePizza extends CheesePizza{

    public LDCheesePizza(){
        name="伦敦的奶酪披萨";
    }

    @Override
    public void prepare() {
        System.out.println(name+"prepare...");
    }

}
