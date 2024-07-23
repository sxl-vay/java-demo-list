package top.boking.aop.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author shxl
 * @Date 2024/7/23 00:29
 * @Version 1.0
 */
@Service
public class TestBeanA {
    @Autowired
    private TestCacheD testCache;

    @PostConstruct
    public void p() {
        System.out.println("testBeanA:"+testCache.getShxl());
    }
}
