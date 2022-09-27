package top.boking.sharding;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shxl
 * @data 2022/7/22 23:46
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =ShardingApplication.class)
public class Tests {
    public boolean canBeEqual(int[] target, int[] arr) {
        HashMap<Integer,Integer> a = new HashMap<>();
        HashMap<Integer,Integer> b = new HashMap<>();
        for (int i : target) {
            a.put(i,a.getOrDefault(i,0)+1);
        }
        for (int i : arr) {
            b.put(i,b.getOrDefault(i,0)+1);
        }
        if (a.size()!=b.size()) return false;
        for (Map.Entry<Integer, Integer> entry : a.entrySet()) {
            Integer key = entry.getKey();
            Integer integer = a.get(key);
            Integer orDefault = b.getOrDefault(key, 0);
            if (integer != orDefault) return false;


        }
        return true;
    }
}
