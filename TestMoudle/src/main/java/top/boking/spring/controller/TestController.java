package top.boking.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shxl
 * @data 2022/6/29 22:48
 **/
@RestController
public class TestController {
    @Autowired
    private RestTemplate template;

    /**
     * 一个标准请求
     * http://localhost:8090/test/1221?value=111
     *{"pathvariable":"111","id":"1221","key":"hello:8070"}
     * 请求结果
     *
     * @param value
     * @param id
     * @return
     */
    @GetMapping("/test/{id}")
    public Map<String,String> getTest(@RequestParam String value,@PathVariable("id") String id) {


        String msg  = "";
        String forObject = template.getForObject("http://redis/get", String.class);//调用请求获取结果

        Map<String,String> map = new HashMap<>();
        map.put("key",forObject);
        map.put("pathvariable",value);
        map.put("id",id);
        return map;

    }
   // @Value("${server.tomcat.uri-encoding}")
    private String name;

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        String name = "sxl";

        return name;
    }


}
