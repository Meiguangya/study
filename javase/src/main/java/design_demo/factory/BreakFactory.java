package design_demo.factory;

import design_demo.factory.breakfast.BreakFast;
import design_demo.factory.makingsFactory.MakingsFactory;

public interface BreakFactory {

    public BreakFast makeBreakFast(MakingsFactory makingsFactory);

}
