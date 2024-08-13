import top.boking.generic.close.SelfAutoClose;
import top.boking.generic.exception.SelfCheckException;
import top.boking.generic.exception.SelfRuntimeException;

import java.sql.SQLOutput;

/**
 * @Author shxl
 * @Date 2024/8/11 00:06
 * @Version 1.0
 */
public class T2 {
    public static void main(String[] args) {
        try (SelfAutoClose autoClose = new SelfAutoClose()) {

        } catch (Exception e) {

        }
    }

    public static int re() {
        int i = 1;
        try {
            int x = 1/0;
            return i;
        } catch (Exception e) {
            System.out.println("222");
            return i;
        } finally {
            i = 99;
            System.out.println("3333");
        }
        //return i;
    }
}
