package design_demo.adapter_demo.phone;

/**
 * @author water33
 */
public class Voltage5VAdapter implements Voltage5V{

    private Voltage220V voltage220V;

    public Voltage5VAdapter(Voltage220V voltage220V){
        this.voltage220V = voltage220V;
    }

    @Override
    public int getVoltage() {
        System.out.println("我是适配器，我将进行适配");
        int voltage = voltage220V.getVoltage();
        voltage = voltage/44;
        return voltage;
    }


}
