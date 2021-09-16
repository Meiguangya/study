package rest_template;

import com.cool.springbootweb.SpringbootWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author meiguangya
 * @Description
 * @create 2021-09-10 15:53
 */

@SpringBootTest(classes = {SpringbootWebApplication.class})
@RunWith(SpringRunner.class)
public class RestTemplateTest {

    @Autowired
    public RestTemplate restTemplate;

    @Test
    public void testGet(){
        String apiUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww99070c95a68fd862&corpsecret=bQCz3H-Ayo_RA_oXOUW1Wxf6WCGfDic3E8YaH4ZFMHA";
        ResponseEntity<String> result = restTemplate.getForEntity(apiUrl, String.class);
        System.out.println(result);
        System.out.println(result.getBody());
    }

}
