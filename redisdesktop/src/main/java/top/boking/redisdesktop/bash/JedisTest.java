package top.boking.redisdesktop.bash;

import redis.clients.jedis.*;
import redis.clients.util.RedisOutputStream;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author shxl
 * @data 2022/5/30 23:25
 **/
public class JedisTest {
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
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.1.12", 6379);

        // 设置Redis的密码
        jedisShardInfo1.setPassword("123456");

        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }

    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.1.12", 6379);
        String auth = jedis.auth("123456");
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);


    }

    public static void parseStatement(String s) {
        String[] stateArr = s.split(" ");
        if ("set".equalsIgnoreCase(stateArr[0])) {

        }

    }
}
