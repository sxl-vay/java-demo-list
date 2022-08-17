package top.boking.sharding.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.boking.sharding.POJO.RequestPromise;
import top.boking.sharding.POJO.Result;
import top.boking.sharding.entity.Goods;
import top.boking.sharding.mapper.GoodsMapper;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
@Slf4j
public class Construct {

    @Autowired
    private BlockingQueue<RequestPromise> queue;
    @PostConstruct
    public void doConstruct() throws Exception {
        mergeJob();
    }

    @Autowired
    private GoodsMapper mapper;

    private void mergeJob() {
        new Thread(()->{
            while (true) {
                List<RequestPromise> list = new ArrayList<>();
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(3);
                        //log.info("mergeJob working");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                int size1 = queue.size();
                while (queue.peek() != null) {
                    list.add(queue.poll());
                }
                //int stock = mapper.listByCondition(new Goods(1L)).get(0).getCount();
                List<Goods> goodsList = mapper.listByCondition(new Goods(1L));
                Goods goods = goodsList.get(0);
                Integer stock = goods.getCount();

                int sum = list.stream().mapToInt(e -> e.getUserRequestEntity().getCount()).sum();
                int size = list.size();

                log.info("sum:shxl:"+sum+"\tlist:"+size+"queue:size:"+size1);
                if (sum < stock) {
                    stock -= sum;
                    mapper.reduceCountByCondition(new Goods(1L,sum));
                    log.info(Thread.currentThread().getName()+"-----------------------");

                    list.forEach(requestPromise -> {
                        requestPromise.setResult(new Result(true, "ok"));
                        synchronized (requestPromise) {
                            requestPromise.notify();
                        }
                    });
                    continue;
                }
                for (RequestPromise requestPromise : list) {
                    Integer count = requestPromise.getUserRequestEntity().getCount();
                    if (count <= stock) {
                        stock -= count;
                        mapper.reduceCountByCondition(new Goods(1L,sum));
                        log.info(Thread.currentThread().getName()+"-----------------------");
                        requestPromise.setResult(new Result(true, "ok"));
                        synchronized (requestPromise) {
                            requestPromise.notify();
                        }
                    } else {
                        requestPromise.setResult(new Result(false,"库存不足"));
                    }
                }

            }
        },"test").start();
    }
}