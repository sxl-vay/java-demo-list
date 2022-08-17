package top.boking.spring.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author shxl
 * @data 2022/6/29 22:47
 **/
@Configuration
public class TestConfig {

    @Bean
    public RestTemplate getRest(RestTemplateBuilder builder) {
        RestTemplate build = builder.build();
        return build;
    }
}
