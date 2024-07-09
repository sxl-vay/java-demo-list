package top.boking.aop.impl;

import org.springframework.stereotype.Service;

/**
 * @Author shxl
 * @Date 2024/5/20 23:30
 * @Version 1.0
 */
//@Service
public class BeanCon {
    private TestCache testCache;
    private TestCacheC testCacheC;

    public BeanCon(TestCache testCache, TestCacheC testCacheC) {
        System.out.println("BeanCon in shxl::");
        this.testCache = testCache;
        this.testCacheC = testCacheC;
    }
}
