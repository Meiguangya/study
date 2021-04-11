package design_demo.observer;

/**
 * @author water33
 */
public class MainTest {

    public static void main(String[] args) {
        Bob bob = new Bob();

        Cat cat = new Cat();
        Dog dog = new Dog();

        bob.registerAnimal(cat);
        bob.registerAnimal(dog);
        bob.goHome("鱼");

    }

}
