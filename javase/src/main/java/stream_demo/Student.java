package stream_demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author water33
 */
@Data
@AllArgsConstructor
public class Student {

    private String stuNum;
    private String name;
    private int age;


    public static List<Student> getTestList(){
        List<Student> studs = new ArrayList<>();
        studs.add(new Student("1001","马云",10));
        studs.add(new Student("1002","马花藤",8));
        studs.add(new Student("1003","刘强东",15));
        studs.add(new Student("1004","雷军",13));
        studs.add(new Student("1004","雷军",13));
        return  studs;
    }

}
