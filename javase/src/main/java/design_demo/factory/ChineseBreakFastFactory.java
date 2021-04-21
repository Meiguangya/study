package design_demo.factory;

import design_demo.factory.breakfast.BreakFast;
import design_demo.factory.breakfast.ChineseBreakFast;
import design_demo.factory.makingsFactory.MakingsFactory;

/**
 * @author water33
 */
public class ChineseBreakFastFactory implements BreakFactory {

    @Override
    public BreakFast makeBreakFast(MakingsFactory makingsFactory) {
        BreakFast breakFast = new ChineseBreakFast(makingsFactory);
        breakFast.setMakings(makingsFactory.createMakings());
        breakFast.setName("中式早餐");
        return breakFast;
    }

}
