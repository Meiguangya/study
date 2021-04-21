package design_demo.factory_pizza;

import design_demo.factory_pizza.pizza.CheesePizza;
import design_demo.factory_pizza.pizza.GreekPizza;
import design_demo.factory_pizza.pizza.Pizza;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * @author water33
 */
public abstract class AbstractPizzaOrder {

    public AbstractPizzaOrder() {
        Pizza pizza = null;
        String orderType;
        do {
            orderType = getType();
            pizza = createPizza(orderType);
            //输出pizza
            try{
                Optional.of(pizza);
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("=======订购完成");
            }catch (Exception e){
                System.out.println("订购失败，程序退出");
                break;
            }

        } while (true);
    }

    public abstract Pizza createPizza(String orderType);


    private String getType() {
        String type = "";
        try {
            BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("输入你想订购的pizza种类:");
            type = bin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }

}
