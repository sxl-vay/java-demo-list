package top.boking.generic.close;

/**
 * @Author shxl
 * @Date 2024/8/12 22:57
 * @Version 1.0
 */
public class SelfAutoClose implements AutoCloseable{
    @Override
    public void close() throws Exception{
        System.out.println("close self");
        Runtime.getRuntime().halt(1);
    }
}
