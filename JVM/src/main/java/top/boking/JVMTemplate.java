package top.boking;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author shxl
 * @Date 2024/8/8 22:58
 * @Version 1.0
 */
public class JVMTemplate {
    private static List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

    private Map<String, Long> gcCountInfo;

    private String gcName;

    public Map<String, Long> getGcCountInfo() {
        if (gcCountInfo == null) {
            refreshGcInfo();
        }
        return gcCountInfo;
    }

    public Long getTotalGcCount() {
        refreshGcInfo();
        long sum = gcCountInfo.values().stream().mapToLong(Long::longValue).sum();
        return sum;
    }
    public Map<String, Long> refreshGcInfo() {
        gcCountInfo = gcBeans.stream().collect(Collectors.toMap(GarbageCollectorMXBean::getName, GarbageCollectorMXBean::getCollectionCount));
        return gcCountInfo;
    }
}
