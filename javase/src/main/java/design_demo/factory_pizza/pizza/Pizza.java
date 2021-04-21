package design_demo.factory_pizza.pizza;

import lombok.Data;

/**
 * @author water33
 * pizza抽象类
 * pizza的制作流程
 * 1.prepare
 * 2.bake
 * 3.cut
 * 4.box
 */
@Data
public abstract class Pizza {

    String name;

    public abstract void prepare();

    public void bake(){
        System.out.println(name+"烘焙中...");
    }

    public void cut(){
        System.out.println("将"+name+"进行切割");
    }


    public void box(){
        System.out.println("将"+name+"进行打包");
    }



}
