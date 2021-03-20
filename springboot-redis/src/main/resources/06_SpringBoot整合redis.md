> SringBoot2.x 之后，原来的redis被替换成了lettuce

> jedis:采用直连，多个线程操作的话，会有安全问题，避免这个问题，要使用jedis pool连接池

> lettuce:采用netty,实例可以在多个线程中共享，不存在线程安全问题，可以减少线程数据，性能更高


> Springboot所有的配置类 都有一个自动配置类 RedisAutoConfiguration

> 自动配置类都会绑定一个properties配置文件 RedisProperties


```
@Bean
@ConditionalOnMissingBean(
    name = {"redisTemplate"} //我们可以自己定义一个redisTemplate
)
public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, Object> template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}
```
```
//String 类型经常使用 单独拿出来了一个类
@Bean
@ConditionalOnMissingBean
public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}
```
```
序列化
@Nullable
private RedisSerializer keySerializer = null;
@Nullable
private RedisSerializer valueSerializer = null;
@Nullable
private RedisSerializer hashKeySerializer = null;
@Nullable
private RedisSerializer hashValueSerializer = null;
```