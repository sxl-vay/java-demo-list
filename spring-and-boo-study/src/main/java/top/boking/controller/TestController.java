package top.boking.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shxl
 * @Date 2024/7/24 20:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/springtest")
public class TestController {
    @GetMapping("/t1")
    @Transactional
    public String t1(@RequestParam Long second) {

        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "ok";
    }
}
