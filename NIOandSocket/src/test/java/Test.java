import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Test extends Thread {
    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2);
    static AtomicInteger at = new AtomicInteger();

    public void run() {
        while (at.get() < 100000) {


         map.put(at.get(), at.get());
            at.incrementAndGet();
        }
    }
}
