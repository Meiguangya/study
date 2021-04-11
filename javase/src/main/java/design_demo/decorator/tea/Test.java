package design_demo.decorator.tea;

import java.io.*;

/**
 * @author water33
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {

        Tea tea = new GreenTea();
        System.out.println(tea.getDescription()+","+tea.cost());

        Tea redTea = new RedTea();
        Honey honey = new Honey(redTea);
        System.out.println(honey.getDescription()+","+honey.cost());

        FileInputStream fin = new FileInputStream("1.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);
    }

}