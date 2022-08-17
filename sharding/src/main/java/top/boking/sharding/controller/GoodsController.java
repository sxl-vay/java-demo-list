package top.boking.sharding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.boking.sharding.POJO.Result;
import top.boking.sharding.POJO.UserRequestEntity;
import top.boking.sharding.entity.Goods;
import top.boking.sharding.service.impl.GoodsService;

/**
 * @author shxl
 * @data 2022/8/17 15:17
 **/
@RestController
@RequestMapping("/goods")

public class GoodsController {
    @Autowired
    private GoodsService service;

    @GetMapping("/kill")
    public Result killGoods(@RequestParam Long userid, @RequestParam Long orderid, @RequestParam Integer count) {

        Result operate = operate = service.test(new Goods(orderid, count));
       /* try {
            operate = service.operate(new UserRequestEntity(orderid, userid, count));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return operate;

    }

    @GetMapping("/killbatch")
    public Result killBatchGoods(@RequestParam Long userid, @RequestParam Long orderid, @RequestParam Integer count) {

        Result operate = null;
        for (int i = 0; i < 10; i++) {
            operate = service.test(new Goods(orderid, count));

        }
       /* try {
            operate = service.operate(new UserRequestEntity(orderid, userid, count));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return operate;

    }

}
