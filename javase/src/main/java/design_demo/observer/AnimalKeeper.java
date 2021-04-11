package design_demo.observer;

/**
 * @author water33
 */
public interface AnimalKeeper {

    void registerAnimal(Animal animal);

    void removeAnimal(Animal animal);

    void notifyAnimal(String food);

}
