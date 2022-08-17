package top.boking.sharding.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.sharding.entity.Addr;
import top.boking.sharding.service.impl.AddrServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shxl
 * @data 2022/7/22 22:04
 *
 * Addr表默认存储在ds0的默认数据库中，不进行分库分表操作
 *
 *
 **/
@RestController()
@Slf4j
@RequestMapping("/addr")
public class AddrController {
    @Autowired
    private AddrServiceImpl service;

    @GetMapping("/list")
    public Map<String, Object> getlist(@RequestParam("size") Integer size, @RequestParam("index") Integer index) {
        log.info("query");
        Map<String, Object> listPage = service.getListPage(index, size);

        return listPage;
    }

    @GetMapping("/remove")
    public Boolean remove(@RequestParam("name") String name) {
        log.info("remove:"+name);
        return service.remove(name);
    }

    @GetMapping("/add")
    public Boolean addBatch(@RequestParam("message") String message) {
        List<Addr> addrs = new ArrayList<>();
        String[] split = message.split(",");
        for (String s : split) {
            Addr addr = new Addr(null,s, s + ":tip");
            addrs.add(addr);
        }
        return service.insertBatch(addrs);
    }

}
