import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author shxl
 * @data 2022/8/16 21:39
 **/
public class KillDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        KillDemo killDemo = new KillDemo();
        killDemo.mergeJob();
        Thread.sleep(2000L);
        List<Future<Result>> futureList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 100; i++) {
            final Long orderId = i+100L;
            final Long userid = Long.valueOf(i);
            Future<Result> submit = executorService.submit(() -> {
                countDownLatch.countDown();
                Result operate = killDemo.operate(new UserRequest(orderId, userid, 1));
                return operate;
            });
            futureList.add(submit);


        }
        int size = futureList.size();
        System.out.println("futureList.size = " + size);
        futureList.forEach(future ->{
            try {
                Result result = future.get(30, TimeUnit.SECONDS);
                System.out.println("result = " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
    }

    private Integer stock = 11;

    private BlockingQueue<RequestPromise> queue = new LinkedBlockingDeque<>(100);

    /**
     * 用户库存扣减
     * @param userRequest
     * @return
     */
    public Result operate(UserRequest userRequest) throws InterruptedException {
        //TODO 阈值判断
        //TODO
        RequestPromise requestPromise = new RequestPromise(userRequest);
        boolean offer = queue.offer(requestPromise, 100, TimeUnit.MICROSECONDS);
        if (!offer) {
            return new Result(false,"系统繁忙");
        }
        synchronized (requestPromise) {
            try {
                requestPromise.wait(200);
            } catch (InterruptedException e) {
                return new Result(false,"等待超时");
            }


        }

        return requestPromise.getResult();

    }
    public void mergeJob() {
        new Thread(()->{
            List<RequestPromise> list = new ArrayList<>();
            while (true) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (queue.peek() != null) {
                    list.add(queue.poll());
                }
                int sum = list.stream().mapToInt(e -> e.getUserRequest().getCount()).sum();
                if (sum < stock) {
                    stock -= sum;
                    System.out.println("stock sum= " + stock);
                    list.forEach(requestPromise -> {
                        requestPromise.setResult(new Result(true, "ok"));
                        synchronized (requestPromise) {
                            requestPromise.notify();
                        }
                    });
                    continue;
                }
                for (RequestPromise requestPromise : list) {
                    Integer count = requestPromise.getUserRequest().getCount();
                    if (count <= stock) {
                        stock -= count;
                        System.out.println("stock count= " + stock);
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
class RequestPromise{
    @Override
    public String toString() {
        return "RequestPromise{" +
                "userRequest=" + userRequest +
                ", result=" + result +
                '}';
    }

    private UserRequest userRequest;
    private Result result;

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public RequestPromise(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public RequestPromise(UserRequest userRequest, Result result) {
        this.userRequest = userRequest;
        this.result = result;
    }
}
class Result{
    private Boolean success;
    private String msg;

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}

class UserRequest{
    private Long orderId;
    private Long userId;
    private Integer count;

    public UserRequest() {
    }

    public UserRequest(Long orderId, Long userId, Integer count) {
        this.orderId = orderId;
        this.userId = userId;
        this.count = count;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
