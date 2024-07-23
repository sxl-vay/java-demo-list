package top.boking.aop.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Author shxl
 * @Date 2024/5/18 21:33
 * @Version 1.0
 */
@Service
@Data
public class TestCacheD {

    @Value("${shxl.test}")
    private String shxl;

    @Resource
    private NormalBean normalBean;

    public TestCacheD() {
        System.out.println("TestCache");
    }

    @PostConstruct
    public void post() {
        System.out.println(normalBean);
    }
}
