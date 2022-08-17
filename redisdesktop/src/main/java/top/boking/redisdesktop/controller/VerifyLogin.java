package top.boking.redisdesktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.boking.redisdesktop.entity.Token;
import top.boking.redisdesktop.entity.User;
import top.boking.redisdesktop.service.VertifyService;

import java.util.Map;

/**
 * @author shxl
 * @data 2022/5/29 22:13
 **/
@RestController
public class VerifyLogin {


   // @Autowired
    private VertifyService service;

    @PostMapping("/verity")
    public Token getMsg(@RequestParam Map<String, String> param) {

        User user = new User(param.get("username"), param.get("password"));

        Token vetify = service.vetify(user);

        return vetify;
    }

}
