package top.boking.redisdesktop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.boking.redisdesktop.entity.User;

@RestController
public class PageController {

	//通过controller返回html界面
    @GetMapping("/aaa")
    public  User testJumpPage(){
        User user = new User();
        user.setPassword("q34");
        user.setUsername("sdf");
        System.out.println("test");




        return user;
    }
    @Value("${spring.cloud.nacos.server-addr}")
    private String s;
    
    //通过controller返回html界面
    @RequestMapping("/index")
    public  String indexJumpPage(){
        return "index";
    }
    @RequestMapping("/")
    public  String index(){

        return "login";
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/get")
    public String get() {

        return "hello:"+s;
    }


}
