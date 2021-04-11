package design_demo.decorator.bread;

/**
 * @author water33
 */
public class HotDog extends OtherDecorator{

    private Bread bread;

    public HotDog(Bread bread){
        this.bread = bread;
    }

    @Override
    public String getDescription() {
        return bread.getDescription()+"加热狗";
    }

    @Override
    public double cost() {
        return bread.cost()+1.5;
    }
}
