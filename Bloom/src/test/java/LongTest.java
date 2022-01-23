import top.boking.bloom.Bloom;
import top.boking.bloom.BloomLong;

import java.util.Random;
import java.util.concurrent.*;

public class LongTest {
    public static void main(String[] args) {
        Bloom bloomLong = BloomLong.getBloomLong();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1880), Thread::new,new ThreadPoolExecutor.DiscardPolicy());
        int a = 0;
        assert a==1:"sd";
        System.out.println("a = " + a);

        /*bloomLong.insertFast("shxl"+5);
        for (int i = 0; i < 100000000; i++) {
            threadPoolExecutor.submit(()->{
                bloomLong.insertKeySecurity("shxl"+ new Random().nextLong());
                long nowDataNumber = bloomLong.getNowDataNumber();
                System.out.println("nowDataNumber = " + nowDataNumber);
            });

        }
        threadPoolExecutor.shutdown();
        System.out.println("bloomLong.getNowDataNumber() = " + bloomLong.getNowDataNumber());
        bloomLong.insertKey("shxl");
        bloomLong.insertKey("shxl2");
        bloomLong.insertKey("shxl3");
        bloomLong.insertKey("shxl4");
        long nowDataNumber = bloomLong.getNowDataNumber();
        System.out.println("nowDataNumber = " + nowDataNumber);
        System.out.println("bloomLong.checkKey(\"shxl\") = " + bloomLong.checkKey("shxl"));
        boolean shxl5 = bloomLong.checkKey("shxl5");
        System.out.println("shxl5 = " + shxl5);
        double nowMissProbability = bloomLong.getNowMissProbability();
        System.out.println("nowMissProbability = " + nowMissProbability);*/
    }
}
