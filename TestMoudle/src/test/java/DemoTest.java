import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author shxl
 * @data 2022/6/18 13:40
 **/
public class DemoTest {
    public static void main(String[] args) {




        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new SynchronousQueue<>(), Thread::new, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 30; i++) {
            threadPoolExecutor.submit(new Run());
            System.out.println("ok"+i);
        }
        try {
            System.out.println("main fin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
        System.out.println("shutdown");

    }
}

class Run implements Runnable {
    ThreadLocal<String> local = new ThreadLocal<String>();
    @Override
    public void run() {
        System.out.println("in "+Thread.currentThread().getName());
        local.set(this.toString());
        local.remove();
        try {
            Thread.sleep(10L);
            System.out.println("fin "+Thread.currentThread().getName()+":"+local);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
