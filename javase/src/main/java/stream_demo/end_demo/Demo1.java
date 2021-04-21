package stream_demo.end_demo;

import stream_demo.Student;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author water33
 * 流的终止操作
 */
public class Demo1 {

    public static void main(String[] args) {
        //1.匹配与查找
        // allMatch
        boolean allMatch = Student.getTestList().stream()
                .allMatch(student -> student.getAge() > 9);
        System.out.println("allMatch:"+allMatch);

        //anyMatch
        boolean anyMatch = Student.getTestList().stream()
                .anyMatch(student -> student.getAge() > 13);
        System.out.println("allMatch:"+anyMatch);

        //noneMatch
        boolean noneMatch = Student.getTestList().stream()
                .noneMatch(student -> student.getAge() > 20);
        System.out.println("noneMatch:"+noneMatch);


        //findFirst
        Optional<Student> first = Student.getTestList().stream().findFirst();
        System.out.println(first.get());

        //findAny
        Optional<Student> any = Student.getTestList().parallelStream()
                .findAny();
        System.out.println(any.get());


    }

}
