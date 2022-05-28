package top.boking;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.PrintStream;

/**
 * 利用redis的sortset数据类型，完成一个滑动窗口，实现一个简单的限流小样
 * 功能是：限制窗口时间段period内的同一个用户的请求数量不能超过maxcount个。
 */
public class SimpleRateLimiter {
    private Jedis jedis;

    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 利用redis的sortset数据类型，完成一个滑动窗口，实现一个简单的限流小样
     * @param userId 用户的id
     * @param actionKey 用户的请求标识
     * @param period 窗口时间段
     * @param maxCount 最大请求数量
     * @return 窗口时间段内的请求数量是否超过最大请求数量。
     */
    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        //将key根据用户id和请求名称进行格式化
        String key = String.format("hist:%s:%s", userId, actionKey);
        //获取当前时间的毫秒值
        long nowTs = System.currentTimeMillis();
        //将当前请求添加进sortset中，其中的score分数就是当前毫秒值，value不需要关系只要保证唯一就行，所以可以取当前的毫秒值
        jedis.zadd(key, nowTs, "" + nowTs);
        //将窗口时间段之前的所有数据删除
        jedis.zremrangeByScore(key, 0, nowTs - period * 1000);
        //获取当前key的value值
        Long zcard = jedis.zcard(key);
        System.out.println("zcard = " + zcard);
        //将整个key设置过期时间
        jedis.expire(key, period + 1);
        //将最大请求数量和当前key的value数量对比，返回是否限流
        return zcard <= maxCount;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
        //for (int i = 0; i < 20; i++)
            System.out.println(limiter.isActionAllowed("laoqian", "reply", 1000, 5));
        PrintStream out = System.out;
    }
}
