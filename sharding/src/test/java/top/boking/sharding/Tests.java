package top.boking.sharding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.boking.sharding.mapper.AddrMapper;
import top.boking.sharding.mapper.UserMapper;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author shxl
 * @data 2022/7/22 23:46
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =ShardingApplication.class)
public class Tests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddrMapper addrMapper;
    @Test
    public void run() {



    }
}
