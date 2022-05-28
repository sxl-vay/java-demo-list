package top.boking;

import java.util.HashMap;
import java.util.Map;

/**
 * 单体式的漏斗funnel算法。
 * 无需redis支持java原生实现，但是分布式环境下存在问题
 *
 * 核心算法是makespace()方法
 * 漏斗需要两个入参
 * capacity：漏洞的总容量
 * leakingRate：泄露率，流水速度
 * makespace通过上次的漏水时间和当前时间的差值乘以流水速度，计算出当前的漏洞空间剩余量。从而给出当前的请求是否接受的响应
 *
 * Redis提供throttle
 */
public class FunnelRateLimiter {
    static class Funnel {
        int capacity;//漏洞容量
        float leakingRate;//漏嘴流水速度
        int leftQuota;//漏斗剩余空间
        long leakingTs;//上一次漏水时间。

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            int deltaQuota = (int) (deltaTs * leakingRate);
            if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            if (deltaQuota < 1) { // 腾出空间太小，最小单位是 1
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota) {
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String, Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1); // 需要 1 个 quota
    }
}
