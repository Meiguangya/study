package design_demo.factory;

import design_demo.factory.breakfast.BreakFast;
import design_demo.factory.makingsFactory.HighMakingsFactory;
import design_demo.factory.makingsFactory.NormalMakingsFactory;

/**
 * @author water33
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("起床了，我要吃早餐～～");

        //BreakFast breakFast = SimpleBreakFastFactory.makeBreakFast();
        BreakFactory factory = new ChineseBreakFastFactory();
        BreakFast breakFast = factory.makeBreakFast(new HighMakingsFactory());

        BreakFactory factory2 = new AmericanBreakFactory();
        BreakFast breakFast2 = factory2.makeBreakFast(new NormalMakingsFactory());

        System.out.println(breakFast);
        System.out.println(breakFast2);


    }

}
