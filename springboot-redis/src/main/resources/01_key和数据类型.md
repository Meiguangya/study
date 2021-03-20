#常用命令
> ping pong 

> 默认16个数据库 

```
select 0  //选择第一个数据库
```

> 清空数据库 
```
flushdb //清空当前数据库
flushall //清空所有数据库
```

> 一些关于key的命令 
```
dbsize 查看多少key
keys * 查看所有的key
ttl key 查看还有多少时间过期  -1 永不过期  -2 已经过期或者不存在这个key
del key 删除key
del key1 key2批量删除 
exists key 查看key是否存在
mset k1 v1 k2 v2 k3 v3
mget k1 k2 k3
getset k1 vv1  如果k1不存在就set k1 vv1 ，返回null

//对于值是integer 类型
incr k1  加
decr k1  减

对于值是String类型
set name "zhangsan"
strlen name => 8 返回字符串长度
append name " lisi" 返回添加后的字符串长度
```

> List数据类型
```
//增
lpush list el1  把元素添加到列表的头部
rpush list el2  添加元素到尾部

//删
lpop list 移除头部的第一个元素
rpop list 移除尾部的第一个元素

//改
lrem list 2 3   移除list中的值  移除2个值为3的元素
ltrim list 0 2  把list从0到2截取出来 重新赋值给list
lset list 0 java1  将list 的第一个元素值设为java1 如果元素不存在 报错  list 不存在也会报错
linsert list before java hello 往java前面插入hello
linsert list after java spring 往java后面插入spring

//查
lrange list 0 -1  查询所有
lrange list 0 2   查询下标 0 1 2 的元素
lindex list 1     查询下标为 1 的元素
llen list         查询list 长度

组合操作
rpoplpush list list2  将list尾部的第一个元素移到list2的头部第一个元素
```


> set数据类型
```
增
sadd set java
sadd set spring
sadd set boot

删
srem set java  删除java元素
spop set       随机删除集合元素
srandmember set  随机拿出一个元素

改
smove set1 set2 java 将set1中的java元素 放到 set2 中

查
smerbers set 查看所有元素
sismember set Java  查看元素是否存在
scard set           查看set大小

聚合操作
sdiff set1 set2   set1有 set2 没有
sdiff set2 set1   set2有 set1 没有

交集
sinter set1 set2  set1和set2都有的  例如 共同关注

并集
sunion set1 set2  set1和set2组合起来

```

> Hash
```
增
hset h1 k1 v1
hmset h1 k2 v2 k3 v3
hsetnx h1 k1 v1   h1中不存在k1的时候设置k1=v1 存在k1则失败

删
hdel h1 k1

改
hset h1 k1 v111

查
hget h1 k1
hlen h1     获取元素个数
hgetall h1  获取所有元素
hexists h1 k1  键是否存在
hkeys h1    获取所有的key
hvals h1    获取所有的值

```


> ZSet
```
增
zadd zset 1 one
zadd zset 2 two 3 three

删
zrem zset java

改
zadd zset 1 oneone

查
zrange myset 0 -1
zrangebyscore myset -inf +inf  //从小到大
zrangebyscore zset -inf +inf withscores  //带序号排名
zrevrange zset 0 -1 //从大到小

zcard zset  获取个数
zcount zset 0 100
```
