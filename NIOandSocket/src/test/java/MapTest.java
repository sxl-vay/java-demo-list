import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        System.out.println("do it ");
        Test t0 = new Test();
        Test t1 = new Test();
        Test t2 = new Test();
        Test t3 = new Test();
        Test t4 = new Test();
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
