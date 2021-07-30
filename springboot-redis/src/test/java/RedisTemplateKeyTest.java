import com.cool.RedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTemplateKeyTest {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    public String flushdb() {
        Object obj = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return "ok";
            }
        });
        return obj.toString();
    }

    @Test
    public void testKey() {
        System.out.println("清空数据：" + flushdb());
        System.out.println("判断某个键是否存在：" + redisTemplate.hasKey("username"));
        System.out.println("新增<'username','java'>的键值对：");
        redisTemplate.opsForValue().set("username", "java");
        System.out.println("新增<'password','password'>的键值对：");
        redisTemplate.opsForValue().set("password", "pwd");

        System.out.print("系统中所有的键如下：");
        redisTemplate.keys("*");

        System.out.println("删除键password:");
        redisTemplate.delete("password");
        System.out.println("判断键password是否存在：" + redisTemplate.hasKey("password"));

        System.out.println("查看键username所存储的值的类型：" + redisTemplate.type("username"));

        System.out.println("随机返回key空间的一个：" + redisTemplate.randomKey());

        System.out.println("重命名key：");
        redisTemplate.rename("username", "name");

        System.out.println("取出改后的name：" + redisTemplate.opsForValue().get("name"));


        System.out.println("删除当前选择数据库中的所有key：" + flushdb());
        System.out.println("返回当前数据库中key的数目：" + redisTemplate.getConnectionFactory().getConnection().dbSize());

        /**
         * System.out.println("清空数据："+jedis.flushDB());


         System.out.println("查看键username所存储的值的类型："+jedis.type("username"));
         System.out.println("随机返回key空间的一个："+jedis.randomKey());
         System.out.println("重命名key："+jedis.rename("username","name"));
         System.out.println("取出改后的name："+jedis.get("name"));
         System.out.println("按索引查询："+jedis.select(0));
         System.out.println("删除当前选择数据库中的所有key："+jedis.flushDB());
         System.out.println("返回当前数据库中key的数目："+jedis.dbSize());
         System.out.println("删除所有数据库中的所有key："+jedis.flushAll());
         */
    }


}
