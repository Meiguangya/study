# 工作中 一些小小的配置 会让你脱颖而出

> 启动的时候 通过配置文件来启动
```
redis-server ./redis.conf
```

```
# 1k => 1000 bytes
# 1kb => 1024 bytes
# 1m => 1000000 bytes
# 1mb => 1024*1024 bytes
# 1g => 1000000000 bytes
# 1gb => 1024*1024*1024 bytes
#
# units are case insensitive so 1GB 1Gb 1gB are all the same.
(大小写不敏感)
```

```
（包含 引入其他的配置文件）
# include /path/to/local.conf
# include /path/to/other.conf
```

```
========NETWORK========
（绑定的IP）
# bind 192.168.1.100 10.0.0.1
# bind 127.0.0.1 ::1

（端口）
port 6379

(是否是受保护的)
protected-mode yes
```

```
######## GENERAL（通用的） #########

daemonize no  是否以守护进程开启 默认是no, 我们自己要把它改成yes

pidfile /var/run/redis_6379.pid  如果以守护进程开启，需要指定一个pid文件

（日志级别）
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably) 生产环境适用
# warning (only very important / critical messages are logged) 关键的信息才会打印

loglevel notice

logfile ""  日志的文件名

databases 16  数据库的数量 默认16个数据库

always-show-logo yes  是否显示logo  启动的时候那个logo
```


> 持久化 在规定的时间内 执行了多少次操作 则会持久化到文件 .rdb文件和 .aof文件

```
######### SNAPSHOTTING 快照#######
redis是内存数据库 如果没有持久化 那么就会断电即失

save 900 1   （900秒内至少有一个key进行修改，就进行持久化操作）
save 300 10    （300秒被至少有10个key进行修改，进行持久化操作）
save 60 10000  （60秒内 有10000个key进行修改，就进行持久化）

stop-writes-on-bgsave-error yes  持久化出错了 是否还要持续工作

rdbcompression yes  是否压缩持久化的文件，会消耗一点CPU的资源

rdbchecksum yes  保存rdb文件的时候是否进行错误的校验

dir ./  rdb文件保存的目录
```

> 安全相关配置
```
#### SECURITY ######
# requirepass foobared  可以自己设置密码 默认没有设置密码


可以在cli中通过命令设置密码
conf get requirepass
conf set requirepass 123456

auth 123456  设置完后要进行登录
```

> 客户端的限制
```
#### CLIENTS ######
# maxclients 10000  设置redis最大客户端的数量

##### MEMORY MANAGEMENT ####
# maxmemory <bytes>  配置最大的内存容量

（内存满了 如何操作）
# maxmemory-policy noeviction
LFU (Least frequently used) 最不经常使用，如果一个数据在最近一段时间内使用次数很少，那么在将来一段时间内被使用的可能性也很小。
# volatile-lfu -> 只对设置了过期时间的key进行lfu   Evict using approximated LFU among the keys with an expire set.
# allkeys-lfu ->  删除lfu算法的key  Evict any key using approximated LFU.
# volatile-random -> 随机删除即将过期的key  Remove a random key among the ones with an expire set.
# allkeys-random -> 随机删除key   Remove a random key, any key.
# volatile-ttl -> 删除即将过期的key Remove the key with the nearest expire time (minor TTL)
# noeviction -> Don't evict anything, just return an error on write operations.
```

> aof的配置（在所有的情况下 rdb够用了 没必要开启）
```
#### APPEND ONLY MODE ########

appendonly no    默认不开启
appendfilename "appendonly.aof"   aof持久化的文件名

同步
# appendfsync always  每次修改同步
appendfsync everysec  每秒同步
# appendfsync no      不同步
```

>主从复制的配置
```
##### REPLICATION #######
# replicaof <masterip> <masterport> 如果配置了这个 那么自己就会变成从节点
# masterauth <master-password>      如果主节点有密码 需要填写
replica-read-only yes               从节点是否readonly
```

