import top.boking.bloom.BloomFilter;
import top.boking.bloom.MemorySize;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MathTest {
    public static void main(String[] args) {
       /* int i = Runtime.getRuntime().availableProcessors();
        System.out.println("i = " + i);*/

        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>(32);
         objectObjectConcurrentHashMap.put("sd", "sd");

    }

    /**
     * @param i    要输入的hashcode
     * @param move 偏移量
     * @return 索引下标
     * <p>
     * 例如：现在开辟了128M内存（需要30位的地址线），所以偏移量是25
     * 有一个hashcode 值i 二进制表示是 0100 1001 1001 1010 1111 0110 1010 1111
     * 偏移量是25，所以开辟了2的25次方的数组大小
     * 所以它要将第00 1001 1001 1010 1111 0110 101（二进制）个数组的第0 1111位 置为1；
     * 数组索引位置相当于将i掐掉前两位（32-30）和后五位
     */
    public static int getIndex(int i, int move) {


        int b = i >>> 5 << 5;//清楚后置位的i
        //要清楚的前置位数
        int p = 32 - 5 - move;
        //要保留的位数
        int save = 32 - p - 5;
        int i1 = (-1) >>> 32 - save;
        //最终和清楚后置位的i进行与的数
        int i2 = i1 << 5;
        //索引下标
        int i3 = (i2 & b) >> 5;

//        化简后得到：（编译期去除掉冗余括号后的最简结果）
        int index = (-1 >>> 32 - move << 5 & i >>> 5 << 5) >> 5;


        System.out.println("最终和清楚后置位的i进行与的数");
        System.out.println("p = " + p);
        System.out.println(Integer.toBinaryString(i2));

        System.out.println("要保留的1的个数");
        System.out.println(Integer.toBinaryString(i1));
        System.out.println("save = " + save);

        System.out.println("索引下标");
        System.out.println(Integer.toBinaryString(i3));
        System.out.println(Integer.toBinaryString(i));


        System.out.println("要保留的中间数");

        System.out.println("清楚后置位后的i");
        System.out.println(Integer.toBinaryString(b));
        return i3;
    }



    public double getNowMissProbability() {
        long nowDataNumber = 900000000L;
        String s = Long.toBinaryString(128);
        System.out.println("s = " + s);
        int K_USEHASHCODENUMBER = 20;
        long REALMEMORYBIT= 128*1024*1024*8;
        double f = nowDataNumber * K_USEHASHCODENUMBER;
        double e = f / REALMEMORYBIT;
        double v = 1 - Math.pow(Math.E, -e);

        double pow = Math.pow(v, REALMEMORYBIT);

        return pow ;
    }
}
