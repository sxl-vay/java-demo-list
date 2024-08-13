import org.springframework.util.StopWatch;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shxl
 * @data 2022/7/5 21:23
 **/
public class Test {


    public Test(String x) {
    }

    public Test() {
    }

    private static WeakReference<Test> testSoftReference = new WeakReference<>(new Test());
    private static WeakReference<String> stringsoft = new WeakReference<>("shxltest");

    public static void main(String[] args) throws Exception{



        int a = getString(1).hashCode();
        int b = a;


        for (int x = 0; a == b; x++) {
            b = getString(x).hashCode();
            printGC();
            Integer.toBinaryString(x).intern();
        }

       /* int i1 = in();
        int i2 = in();
        for (int i = 0; i1 == i2; i++) {
            i2 = in();
            String s = new String("xxxxxxx");
            int i3 = s.hashCode();
            System.out.println("i2:" + i2 + "  i3:" + i3);
        }
*/
        System.out.println("ok");
    }

    public static int in() {
        int i2 = "xxxxxxx".hashCode();
        ArrayList<Test> strings = new ArrayList<>(100000);
        strings.add(new Test());
        return i2;
    }

    public static Test getTest(int x) {
        ArrayList<Test> strings = new ArrayList<>(100000);

        if (testSoftReference.get() == null) {
            System.out.println(x);
            testSoftReference = new WeakReference<>(new Test());
           return new Test("shxltest");
        }
        return testSoftReference.get();

    }

    public static String getString(int x) {
        ArrayList<Test> strings = new ArrayList<>(100000);
        if (stringsoft.get() == null) {
            System.out.println(x+"string");
            stringsoft = new WeakReference<>("shxltest");
            return "shxltest";
        }
        return stringsoft.get();

    }

    public static void printGC() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("GC Name: " + gcBean.getName());
            System.out.println("Number of collections: " + gcBean.getCollectionCount());
            System.out.println("Collection time: " + gcBean.getCollectionTime() + "ms");
        }
    }
}
