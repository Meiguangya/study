package stream_demo.end_demo;

import stream_demo.Student;

import java.util.Comparator;
import java.util.Optional;

/**
 * @author water33
 * 流的终止操作
 * end max min
 */
public class Demo2 {

    public static void main(String[] args) {

        //1.count
        long count = Student.getTestList().stream()
                .count();
        System.out.println(count);


        //2.min
        Optional<Student> min = Student.getTestList().stream()
                .min((stu1, stu2) -> {
                    if (stu1.getAge() == stu2.getAge()) {
                        return stu1.getStuNum().compareToIgnoreCase(stu2.getStuNum());
                    } else {
                        return stu1.getAge() - stu2.getAge();
                    }
                });
        System.out.println(min.get());

        //3.max
        Optional<Student> max = Student.getTestList().stream()
                .max((stu1, stu2) -> {
                    if (stu1.getAge() == stu2.getAge()) {
                        return stu1.getStuNum().compareToIgnoreCase(stu2.getStuNum());
                    } else {
                        return stu1.getAge() - stu2.getAge();
                    }
                });
        System.out.println(max.get());


    }

}
