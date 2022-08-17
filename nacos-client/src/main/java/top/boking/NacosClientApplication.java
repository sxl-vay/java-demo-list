package top.boking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import top.boking.config.ConfigTest;

/**
 * @author shxl
 * @data 2022/7/2 10:35
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NacosClientApplication.class, args);
        String username = run.getEnvironment().getProperty("shxl");
        System.out.println("username = " + username);
        ConfigTest configTest = new ConfigTest();
        configTest.getStr();
    }
}
