package design_demo.decorator.tea;

/**
 * @author water33
 */
public class Honey extends CondimentDecorator{

    Tea tea;

    public Honey(Tea tea){
        this.tea = tea;
    }

    @Override
    public double cost() {
        return 1.7+tea.cost();
    }

    @Override
    public String getDescription() {
        return tea.getDescription()+",加蜂蜜";
    }
}
