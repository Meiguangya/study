package design_demo.factory_pizza;

/**
 * @author water33
 */
public class PizzaStore {

    public static void main(String[] args) {
        System.out.println("创建pizza订单");
        //new PizzaOrder();
        AbstractPizzaOrder pizzaOrder = new BJPizzaOrder();

    }

}
