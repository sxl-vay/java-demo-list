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
public class TestCacheD {
    @Resource
    private TestCache testCache;
    @Cacheable(keyName = "test", expireTime = 10000)
    public String test() {
        System.out.println("test in "+testCache.getClass());
        return "test";
    }

    public TestCacheD() {
        System.out.println("TestCache");
    }
}
