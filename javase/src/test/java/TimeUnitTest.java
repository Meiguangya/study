import org.junit.Test;

import java.util.concurrent.TimeUnit;

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

}
