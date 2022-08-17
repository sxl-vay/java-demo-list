package demo.test;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author shxl
 * @data 2022/8/17 18:12
 **/
public class FutureTest implements Callable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            list.add(executorService.submit(new FutureTest()));

        }
        list.forEach(stringFuture -> {
            boolean done = stringFuture.isDone();
            /*while (!done) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                done = stringFuture.isDone();
                System.out.println("done = " + done);

            }
            System.out.println("done = " + done);*/
            String o = null;
            try {
                o = stringFuture.get(8,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("o = " + o);

        });


    }


    @Override
    public String call() throws Exception {

        Thread.sleep(10000);
        return Thread.currentThread().getName();
    }
}
