package weaver.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = SpringBootConfiguration.class)
public class WebSocketStarter {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketStarter.class,args);
    }
}
