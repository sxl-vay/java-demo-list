package top.boking.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author shxl
 * @data 2022/7/2 18:23
 **/
public class ConfigTest {
    @Value("${spring.application.name}")
    private String test;
    public void getStr() {

    }

}
