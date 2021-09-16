import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author meiguangya
 * @Description
 * @create 2021-09-02 9:30
 */
public class StreamTest {

    String[] words = {"Hello","World"};

    @Test
    public void test1(){

        List<String> collect1 = Arrays.stream(words).collect(Collectors.toList());

//        Arrays.stream(words)
//            .map(word -> word.split(""))
//            .map(Arrays::stream)
//            .collect(Collectors.toList());

        Stream<String[]> stream = Arrays.asList(words).stream().
                       map(str -> str.split(""));

        Stream<Stream<String>> streamStream = stream.map(strings1 -> Arrays.stream(strings1));
        List<Stream<String>> collect = streamStream.map(s -> s.map(ss -> ss)).collect(Collectors.toList());


        collect.forEach(System.out::println);

    }

}
