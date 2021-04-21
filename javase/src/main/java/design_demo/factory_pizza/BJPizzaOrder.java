package design_demo.factory_pizza;

import design_demo.factory_pizza.pizza.*;

/**
 * @author water33
 */
public class BJPizzaOrder extends AbstractPizzaOrder{

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BJCheesePizza();
        } else if ("greek".equals(orderType)) {
            pizza = new BjGreekPizza();
        }
        return pizza;
    }

}
