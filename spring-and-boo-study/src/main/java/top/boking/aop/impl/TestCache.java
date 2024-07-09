package top.boking.aop.impl;

import org.springframework.stereotype.Service;
import top.boking.aop.Cacheable;

import javax.annotation.Resource;

/**
 * @Author shxl
 * @Date 2024/5/18 21:33
 * @Version 1.0
 */
@Service
public class TestCache {
    @Resource
    private TestCacheC testCacheC;
    @Cacheable(keyName = "test", expireTime = 10000)
    public String test() {
        System.out.println("test in "+testCacheC.getClass());
        return "test";
    }

    public TestCache() {
        System.out.println("TestCache");
    }
}
