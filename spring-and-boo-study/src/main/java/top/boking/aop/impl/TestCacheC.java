package top.boking.aop.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import top.boking.aop.Cacheable;

import javax.annotation.Resource;

/**
 * @Author shxl
 * @Date 2024/5/18 21:33
 * @Version 1.0
 */
@Service
public class TestCacheC {
    @Cacheable(keyName = "test", expireTime = 10000)
    public String test() {
        System.out.println("test in ");
        return "test";
    }

    @Value("${shxl.test}")
    private String shxl;



    public TestCacheC() {
    }


}
