package stream_demo.end_demo;

import stream_demo.Student;

import java.util.Optional;

/**
 * @author water33
 * reduce 归约
 */
public class Demo3 {

    public static void main(String[] args) {
        Optional<Integer> ageSum = Student.getTestList().stream()
                .map(stu -> stu.getAge())
                .reduce((a1, a2) -> a1 + a2);

        System.out.println(ageSum.get());

    }

}
