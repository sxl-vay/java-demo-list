package top.boking;

import redis.clients.jedis.*;
import redis.clients.util.RedisOutputStream;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.1.12", 6379);

        // 设置Redis的密码
        jedisShardInfo1.setPassword("123456");

        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();
        // 插入key-value
        String keys = "hellos";
        String vaule = jedis.set(keys, "aaaaa");
        Class<RedisOutputStream> redisOutputStreamClass = RedisOutputStream.class;
        Method[] declaredMethods = redisOutputStreamClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            String name = declaredMethod.getName();
            System.out.println("name = " + name);
        }
      /*  Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            if ("exit".equalsIgnoreCase(s)) {
                return;
            }

        }*/



    }

    public static void parseStatement(String s) {
        String[] stateArr = s.split(" ");
        if ("set".equalsIgnoreCase(stateArr[0])) {

        }

    }

}
