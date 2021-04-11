package design_demo.decorator.tea;

/**
 * @author water33
 */
public class Sugar extends CondimentDecorator {

    Tea tea;

    public Sugar(Tea tea){
        this.tea = tea;
    }

    @Override
    public double cost() {
        return 1.3;
    }

    @Override
    public String getDescription() {
        return tea.getDescription()+",加糖";
    }
}
