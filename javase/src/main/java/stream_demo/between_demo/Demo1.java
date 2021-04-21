package stream_demo.between_demo;

import stream_demo.Student;

/**
 * @author water33
 * 流的一些中间操作
 * filter  limit  skip  distinct
 */
public class Demo1 {

    public static void main(String[] args) {

        //1.过滤
        Student.getTestList().stream().filter(s->{return s.getAge()>10;})
                .forEach(System.out::println);
        System.out.println("============");
        //2.limit
        Student.getTestList().stream().filter(s->{return s.getAge()>10;})
                .limit(2)
                .forEach(System.out::println);
        System.out.println("============");

        //3.skip
        Student.getTestList().stream().filter(s->{return s.getAge()>10;})
                .limit(2)
                .skip(1)
                .forEach(System.out::println);
        System.out.println("============");

        //4.distinct
        Student.getTestList().stream().filter(s->{return s.getAge()>10;})
                .distinct()
                .forEach(System.out::println);
        System.out.println("============");

    }

}
