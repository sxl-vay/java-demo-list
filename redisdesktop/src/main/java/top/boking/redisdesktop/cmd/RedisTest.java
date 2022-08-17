package top.boking.redisdesktop.cmd;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author shxl
 * @data 2022/7/3 17:49
 **/
public class RedisTest {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("192.168.1.11", 6379);
        Jedis resource = jedisPool.getResource();
        String auth = resource.auth("123456");
        String a = resource.get("a");
        System.out.println("a = " + a);

    }
}
