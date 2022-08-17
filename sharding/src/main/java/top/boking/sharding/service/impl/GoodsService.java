package top.boking.sharding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.boking.sharding.POJO.RequestPromise;
import top.boking.sharding.POJO.Result;
import top.boking.sharding.POJO.UserRequestEntity;
import top.boking.sharding.entity.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author shxl
 * @data 2022/8/17 15:02
 **/
@Service
public class GoodsService {

    @Autowired
    private BlockingQueue<RequestPromise> queue;

    @Autowired
    private ExecutorService executorService;

    public Result test(Goods goods) {

        Future<Result> submit = executorService.submit(() -> {
            Result operate = operate(new UserRequestEntity(goods.getOrderId(), 1L, goods.getCount()));
            System.out.println("operate = " + operate);
            return operate;
        });
        Result result = new Result(false, "error");
        try {
            boolean done = submit.isDone();
           /* while (!done) {
                done = submit.isDone();
                System.out.println("done = " + done);
            }*/
            result = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Result operate(UserRequestEntity UserRequestEntity) throws InterruptedException {
        //TODO 阈值判断
        //TODO
        RequestPromise requestPromise = new RequestPromise(UserRequestEntity);
        boolean offer = queue.offer(requestPromise, 100, TimeUnit.MICROSECONDS);
        if (!offer) {
            return new Result(false, "系统繁忙");
        }
        synchronized (requestPromise) {
            try {
                requestPromise.wait(200);
            } catch (InterruptedException e) {
                return new Result(false, "等待超时");
            }


        }

        return requestPromise.getResult();

    }
}
