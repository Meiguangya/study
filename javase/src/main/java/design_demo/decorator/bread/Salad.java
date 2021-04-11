package design_demo.decorator.bread;

/**
 * @author water33
 */
public class Salad extends OtherDecorator{

    private Bread bread;

    public Salad(Bread bread){
        this.bread = bread;
    }

    @Override
    public String getDescription() {
        return bread.getDescription()+"加沙拉";
    }

    @Override
    public double cost() {
        return bread.cost()+2.0;
    }
}
