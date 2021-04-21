package design_demo.factory.breakfast;

import lombok.Data;

/**
 * @author water33
 */
public abstract class BreakFast {

    String name;
    String makings;

    abstract void getMakings();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMakings(String makings) {
        this.makings = makings;
    }

    @Override
    public String toString() {
        return "BreakFast{" +
                "name='" + name + '\'' +
                ", makings='" + makings + '\'' +
                '}';
    }
}
