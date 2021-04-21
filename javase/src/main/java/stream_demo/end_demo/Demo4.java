package stream_demo.end_demo;

import stream_demo.Student;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author water33
 */
public class Demo4 {

    public static void main(String[] args) {

        Set<Student> set = Student.getTestList().stream()
                .filter(stu -> stu.getAge() > 10)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);
    }

}
