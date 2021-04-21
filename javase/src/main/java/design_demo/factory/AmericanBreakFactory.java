package design_demo.factory;

import design_demo.factory.breakfast.AmericanBreakFast;
import design_demo.factory.breakfast.BreakFast;
import design_demo.factory.makingsFactory.MakingsFactory;

/**
 * @author water33
 */
public class AmericanBreakFactory implements BreakFactory{
    @Override
    public BreakFast makeBreakFast(MakingsFactory makingsFactory) {
        AmericanBreakFast breakFast = new AmericanBreakFast(makingsFactory);
        breakFast.setName("美式早餐");
        breakFast.setMakings(makingsFactory.createMakings());
        return breakFast;
    }
}
