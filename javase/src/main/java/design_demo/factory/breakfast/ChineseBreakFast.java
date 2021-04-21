package design_demo.factory.breakfast;

import design_demo.factory.makingsFactory.MakingsFactory;

/**
 * @author water33
 */
public class ChineseBreakFast extends BreakFast{

    private MakingsFactory makingsFactory;

    public ChineseBreakFast(MakingsFactory makingsFactory){
        this.makingsFactory = makingsFactory;
    }

    @Override
    void getMakings() {
        this.makings = makingsFactory.createMakings();
    }
}
