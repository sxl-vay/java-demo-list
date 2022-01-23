package top.boking.springbootrabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController
public class RedisController {
    @Autowired
    private JedisConnectionFactory factory;

    @GetMapping("/redis/{id}/{value}")
    public String setKey(@PathVariable String id,@PathVariable String value){
        RedisConnection connection = factory.getConnection();
        Boolean isSet = connection.setNX(id.getBytes(), value.getBytes());
        if (isSet) {
            return value;
        }
        return "键已存在";
    }

    @GetMapping("/redis/get/{id}")
    public String getKey(@PathVariable String id){
        RedisConnection connection = factory.getConnection();
        byte[] bytes = connection.get(id.getBytes());
        String s = new String(bytes);

        return s;
    }



}
