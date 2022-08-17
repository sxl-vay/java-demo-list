package top.boking.sharding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.boking.sharding.POJO.RequestPromise;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author shxl
 * @data 2022/8/17 15:12
 **/
@Configuration
public class GoodsKillConfiguration {

    @Bean("executorServices")
    public ExecutorService getExecutorService(){
        return Executors.newCachedThreadPool();
    }

    @Bean
    public BlockingQueue<RequestPromise> getQueue() {

        return new LinkedBlockingDeque<>(100);
    }
}
