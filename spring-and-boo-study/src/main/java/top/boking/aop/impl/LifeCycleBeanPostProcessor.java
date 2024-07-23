package top.boking.aop.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * @Author shxl
 * @Date 2024/7/23 21:03
 * @Version 1.0
 */
@Component
public class LifeCycleBeanPostProcessor implements
        InstantiationAwareBeanPostProcessor {
    private static final String name = "LifeCycleBeanPostProcessor::";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            System.out.println(name+"postProcessBeforeInitialization"+beanName);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            System.out.println(name+"postProcessAfterInitialization"+beanName);

        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            System.out.println(name+"postProcessBeforeInstantiation:" + beanName);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            System.out.println(name+"postProcessAfterInstantiation:" + beanName);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            System.out.println(name+"postProcessProperties:" + beanName);
            if (bean instanceof LifeCycleBean) {
                LifeCycleBean lifeCycleBean = (LifeCycleBean) bean;
                lifeCycleBean.setShxl("lifecycle shxl");
                NormalBean normalBean = new NormalBean();
                lifeCycleBean.setNormalBean(normalBean);
                PropertyValue propertyValue = new PropertyValue("shxl","lifecycle shxl");
                MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
                mutablePropertyValues.add("shxl","propertyValue");
                MutablePropertyValues mutablePropertyValues1 = (MutablePropertyValues) pvs;
                mutablePropertyValues1.add("shxl","propertyValue111");
                normalBean.setId("normal test");

                mutablePropertyValues1.add("normalBean", normalBean);
//                pvs = mutablePropertyValues1
                return null;
            }
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            return null;
        }
            return InstantiationAwareBeanPostProcessor.super.postProcessPropertyValues(pvs, pds, bean, beanName);
        }
}
