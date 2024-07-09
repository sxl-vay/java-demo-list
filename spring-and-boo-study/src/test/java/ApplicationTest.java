import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.boking.SpringBootStarter;
import top.boking.aop.impl.TestCache;

import javax.annotation.Resource;

/**
 * @Author shxl
 * @Date 2024/5/18 21:41
 * @Version 1.0
 */
@SpringBootTest(classes = SpringBootStarter.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTest {
    @Resource
    public TestCache testCache;

    @Test
    public void t1() {
        String test = testCache.test();

    }
}
