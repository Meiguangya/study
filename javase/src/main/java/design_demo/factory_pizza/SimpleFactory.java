package design_demo.factory_pizza;

import design_demo.factory_pizza.pizza.CheesePizza;
import design_demo.factory_pizza.pizza.GreekPizza;
import design_demo.factory_pizza.pizza.Pizza;

/**
 * @author water33
 */
public class SimpleFactory {

    public static Pizza createPizza(String orderType){
        Pizza pizza = null;
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
        }
        return pizza;
    }

}
