package top.boking.moudle.aOJO;

import top.boking.spring.annotation.Bean;
import top.boking.spring.annotation.Component;
import top.boking.spring.annotation.ElementForScope;
import top.boking.spring.annotation.Scope;

@Component("shxl")
@Scope(ElementForScope.SINGLETON)
//@Scope(ElementForScope.PROTOTYPE)
public class Person {
    private String name;
    private Integer age;
    private String addr;

    @Bean("dede")
    private Person de() {
        return null;
    }
}
