package top.boking.aop.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author shxl
 * @Date 2024/7/23 20:20
 * @Version 1.0
 */
@Component("lifeCycleBean")
public class LifeCycleBean implements
        BeanNameAware, BeanFactoryAware, BeanClassLoaderAware
        , InitializingBean {
    @Resource
    private NormalBean normalBean;
    @Value("${shxl.test}")
    private String shxl;

    public LifeCycleBean() {
        System.out.println(name+"Construct");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(name+"postConstruct");
    }

    private static final String name = "LifeCycleBean::";

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println(name + "setBeanClassLoader"+"::shxl:"+shxl+"::normalBean"+normalBean);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(name + "setBeanFactory");

    }

    @Override
    public void setBeanName(String name) {
        System.out.println(LifeCycleBean.name + "setBeanName");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name + "afterPropertiesSet");
    }

    public NormalBean getNormalBean() {
        return normalBean;
    }

    public void setNormalBean(NormalBean normalBean) {
        this.normalBean = normalBean;
    }

    public String getShxl() {
        return shxl;
    }

    public void setShxl(String shxl) {
        this.shxl = shxl;
    }
}
