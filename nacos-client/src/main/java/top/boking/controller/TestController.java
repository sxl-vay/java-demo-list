package top.boking.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shxl
 * @data 2022/7/2 22:46
 **/
@RestController
public class TestController {
    @Value("${shxl}")
    private String sxl;
    String s = "asdfdfd";

    @GetMapping("/shxl")
    public String getSxl() {
        return sxl;

    }
}
