package stream_demo.create_demo;

import stream_demo.Student;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author water33
 */
public class Demo1 {

    public static void main(String[] args) {
        //1.使用集合创建
        Student.getTestList().stream().forEach(System.out::println);

        System.out.println("=======");
        //2.使用数组创建
        Arrays.stream(Student.getTestList().toArray()).forEach(System.out::println);
        System.out.println("=======");

        //3.通过Stream.of
        Stream.of(Student.getTestList()).forEach(System.out::println);

    }

}
