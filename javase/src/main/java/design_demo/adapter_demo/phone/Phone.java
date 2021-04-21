package design_demo.adapter_demo.phone;

/**
 * @author water33
 */
public class Phone {

    public void charge(Voltage5V voltage5V){
        int voltage = voltage5V.getVoltage();
        if(voltage!=5){
            System.out.println("不能充电");
        }
        System.out.println("使用"+voltage+"V电压开始充电");
    }

}
