package design_demo.decorator.bread;

/**
 * @author water33
 */
public class MainTest {

    public static void main(String[] args) {
        //2个热狗普通面包
        Bread twoDogNormalBread = new HotDog(new HotDog(new NormalBread()));

        System.out.println(twoDogNormalBread.getDescription()+",cost:"+twoDogNormalBread.cost());

        //1个热狗1份沙拉肉松面包
        Bread oneDogOneSaladMeatsBread = new HotDog(new Salad(new MeatsBread()));
        System.out.println(oneDogOneSaladMeatsBread.getDescription()+",cost:"+oneDogOneSaladMeatsBread.cost());
    }
}
