package top.boking.moudle.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.boking.moudle.aOJO.Person;
import top.boking.spring.annotation.AutoWired;
import top.boking.spring.annotation.Component;

/**
 * @author shxl
 * @data 2022/5/3 22:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ShiroConfig {
    @AutoWired("shxl")
    private Person person;

    public  void gets(String[] args) {
        String s = person.toString();
        System.out.println("s = " + s);


    }
}
