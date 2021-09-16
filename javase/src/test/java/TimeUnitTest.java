import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author water33
 */
public class TimeUnitTest {


    @Test
    public void test1(){
        System.out.println(TimeUnit.SECONDS.toMillis(1));
        //5 * 60 * 1000
        System.out.println(TimeUnit.MINUTES.toMillis(5));
    }

    @Test
    public void test2(){
        String s1 = "1.30.1";
        String s2 = "1.32";
        System.out.println(compare(s1,s2));
    }

    @Test
    public void test3(){
        String regex = "^(\\d+)\\.(\\d+)\\.(\\d+)$";
        //Pattern pattern = Pattern.compile("\\d.\\d\\.\\d");
        boolean isMatch = Pattern.matches(regex, "v:1.20.1");
        System.out.println(isMatch);
//        Matcher matcher = pattern.matcher("1.0.1");
//        while(matcher.find()){
//            System.out.println(matcher.group(0));
//        }
    }

    @Test
    public void test4(){
        String input = "V1.1.3-ias1.23.30";

        String regex = "(\\d+)\\.(\\d+)\\.(\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean b = matcher.find();
        System.out.println(matcher.group(0));
//        while(matcher.find()){
//            System.out.println(matcher.group(0));
//        }
//        String s1 = "", s2="";
//        if(matcher.find()){
//            s1=matcher.group(0);
//        }
//        if(matcher.find()){
//            s2=matcher.group(0);
//        }
//        System.out.println(s1);
//        System.out.println(s2);
    }


    private int compare(String s1,String s2){
        String[] n1 = s1.split("\\.");
        String[] n2 = s2.split("\\.");
        int min = Math.min(n1.length, n2.length);
        for (int i = 0; i < min; i++) {
            int num1 = Integer.parseInt(n1[i]);
            int num2 = Integer.parseInt(n2[i]);
            if(num1>num2){
                return 1;
            }else if(num1<num2){
                return -1;
            }
        }
        return 0;
    }

}
