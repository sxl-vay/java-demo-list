package top.boking.aop.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.boking.aop.Cacheable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author shxl
 * @Date 2024/5/18 21:33
 * @Version 1.0
 */
@Service
public class TestCache {

    @Value("${shxl.test}")
    private String shxl;

    @Autowired
    private NormalBean normalBean;


    public TestCache() {
        System.out.println("TestCache");
    }

    @PostConstruct
    public void post() {
        System.out.println("cache Test:testCache"+shxl);
    }

    public String getShxl() {
        return shxl;
    }

    public void setShxl(String shxl) {
        this.shxl = shxl;
    }

    public NormalBean getNormalBean() {
        return normalBean;
    }

    public void setNormalBean(NormalBean normalBean) {
        this.normalBean = normalBean;
    }
}
