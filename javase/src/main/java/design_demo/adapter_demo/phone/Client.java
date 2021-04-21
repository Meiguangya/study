package design_demo.adapter_demo.phone;

/**
 * @author water33
 */
public class Client {

    public static void main(String[] args) {
        //我手机只能写5v的，但是现在只有220v的插座
        Phone phone = new Phone();
        phone.charge(new Voltage5VAdapter(new Voltage220V()));
    }

}
