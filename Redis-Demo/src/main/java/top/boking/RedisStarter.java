package top.boking;

import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class RedisStarter {
    private static ShardedJedisPool pool;

    static {
        // 配置Redis信息
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        // 集群
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.88.2", 6379);

        // 设置Redis的密码
        jedisShardInfo1.setPassword("root");

        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();
        // 插入key-value
        String keys = "hello";
        String vaule = jedis.set(keys, "hello redis");
        System.out.println(vaule);
    }
}
