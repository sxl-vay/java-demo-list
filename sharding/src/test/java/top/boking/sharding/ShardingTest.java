package top.boking.sharding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.boking.sharding.entity.Goods;
import top.boking.sharding.entity.User;
import top.boking.sharding.mapper.AddrMapper;
import top.boking.sharding.mapper.GoodsMapper;
import top.boking.sharding.mapper.UserMapper;
import top.boking.sharding.service.impl.GoodsService;

import java.util.*;

/**
 * @author shxl
 * @data 2022/7/23 0:32
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =ShardingApplication.class)
public class ShardingTest {

    @Autowired
    private GoodsMapper goodsMapper;
    @Test
    public void run() {
        Boolean aBoolean = goodsMapper.reduceCountByCondition(new Goods(1L, 5));
        List<Goods> goods = goodsMapper.listAll();
        System.out.println("goods = " + goods);

    }






}
