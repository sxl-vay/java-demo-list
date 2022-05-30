package top.boking.redisdesktop.controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shxl
 * @data 2022/5/29 22:13
 **/
@RestController
@RequestMapping("/a")
public class GetMessage {
    @GetMapping("/msg")
    public String getMsg(String s) {

        System.out.println("s = " + s);
        return "ok";

    }
    @PostMapping("/msg")
    public Map<String, String> getMsgPost(@RequestParam Map s) {

        System.out.println("s = " + s);
        HashMap<String, String> rt = new HashMap<String, String>();
        rt.put("data","ok");

        return rt;

    }
}
