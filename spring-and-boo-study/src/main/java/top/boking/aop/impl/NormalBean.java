package top.boking.aop.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author shxl
 * @Date 2024/7/22 22:23
 * @Version 1.0
 */
@Component
public class NormalBean {

    private String name;

    @Value("${shxl.test}")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NormalBean() {
        System.out.println("NonmalBean");
    }

    @Override
    public String toString() {
        return "NormalBean{" +
                "id='" + id + '\'' +
                '}';
    }
}
