import top.boking.JVMTemplate;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author shxl
 * @Date 2024/8/8 23:07
 * @Version 1.0
 */
public class JVMTest {
    public static void main(String[] args) {
        String s = new String("1xxxx");
        s.intern();
        String s2 = "1xxxx";
        System.out.println(s == s2);

        String s3 = new String("1xxxx") + new String("1xxxx");
        s3.intern();
        String intern = s3.intern();
        String s4 = "1xxxx1xxxx";


    }

    private static void createObj() {
        String intern1 = "shxlTest".intern();
        ArrayList<JVMTemplate> jvmTemplates = new ArrayList<>(100000);
    }
}
