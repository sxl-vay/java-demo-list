import java.lang.ThreadLocal;
 
public class ThreadLocalExample {
    // 使用Lambda表达式初始化ThreadLocal的值
    private static final ThreadLocal<Integer> SEQUENCE_NUMBER = ThreadLocal.withInitial(() -> 0);
 
    public static void main(String[] args) {
        // 在主线程中获取初始化的值
        System.out.println("Main thread sequence number: " + SEQUENCE_NUMBER.get());
 
        // 在新的线程中获取初始化的值
        Thread thread = new Thread(() -> {
            System.out.println("New thread sequence number: " + SEQUENCE_NUMBER.get());
        });
        thread.start();
 
        // 主线程中递增序列号
        SEQUENCE_NUMBER.set(SEQUENCE_NUMBER.get() + 1);
 
        // 新线程中递增序列号
        thread.run();
    }
}