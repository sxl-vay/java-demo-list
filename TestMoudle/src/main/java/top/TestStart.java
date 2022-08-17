package top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shxl
 * @data 2022/6/29 22:45
 **/
@SpringBootApplication(exclude = SpringBootConfiguration.class)

//@EnableDiscoveryClient
public class TestStart {
    public static void main(String[] args) {
        SpringApplication.run(TestStart.class,args);
    }
}
