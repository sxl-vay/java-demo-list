package top.boking.aop.impl;

import org.springframework.stereotype.Service;
import top.boking.aop.Cacheable;

/**
 * @Author shxl
 * @Date 2024/5/18 21:33
 * @Version 1.0
 */
//@Service
public class TestCache {
    @Cacheable(keyName = "test", expireTime = 10000)
    public String test() {
        System.out.println("test in ");
        return "test";
    }

}