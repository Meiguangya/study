package design_demo.observer;

import java.util.ArrayList;

/**
 * @author water33
 */
public class Bob implements AnimalKeeper{

    private ArrayList<Animal> list;

    public Bob(){
        this.list = new ArrayList<>();
    }

    @Override
    public void registerAnimal(Animal animal) {
        list.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        list.remove(animal);
    }

    @Override
    public void notifyAnimal(String food) {
        for (int i = 0; i < list.size(); i++) {
            Animal animal = list.get(i);
            animal.masterHome(food);
        }
    }

    public void goHome(String food){
        System.out.println("Bob 回家了,并且带回了"+food);
        notifyAnimal(food);
    }

}
