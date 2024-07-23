package top.boking.aop.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import top.boking.aop.Cacheable;

/**
 * @Author shxl
 * @Date 2024/5/18 21:33
 * @Version 1.0
 */
@Service
public class InstantationBeanB implements InstantiationAwareBeanPostProcessor {


    public InstantationBeanB() {
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInstantiation:"+beanName);
        if (beanName.equals("testCache")) {
            TestCache testCache = new TestCache();
            testCache.setShxl("zhijiexinjian");
            return testCache;
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInstantiation:"+beanName);
        if (beanName.equals("testCacheD")) {
            TestCacheD testCacheD = (TestCacheD) bean;
            testCacheD.setShxl("InstantationBeanB insert");
            return false;
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("postProcessProperties:"+beanName);
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }
}
