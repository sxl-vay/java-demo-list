package top.boking.springbootrabbitmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("史祥连")
                .select()
                //.apis(RequestHandlerSelectors.basePackage("top.boking.swagger.controller"))//扫描指定的包
                .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }
    @Bean
    public Docket docket1(){

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("二号")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.boking.swagger.controller"))//扫描指定的包
                .build();
    }

    private ApiInfo apiInfo(){
        Contact sxl = new Contact("sxl", "139.196.168.29","1693847306@qq.com");

        ApiInfo apiInfo = new ApiInfo("我的swaggerAPI文档",
                "量变是基石",
                "v1.0",
                "139.196.168.29",
                sxl,
                "",
                "",
                new ArrayList<>());

        return apiInfo;


    }
}
