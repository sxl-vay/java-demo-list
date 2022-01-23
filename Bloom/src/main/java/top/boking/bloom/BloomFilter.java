package top.boking.bloom;

import top.boking.exception.HashCodeMethodNumberOutOfException;
import top.boking.utils.HashCodeLib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 纯java实现的bloom filter
 *
 */
public class BloomFilter {
    private static Logger logger = Logger.getLogger(BloomFilter.class);//log4j日志对象
    private static int[] MEMORY;//位图数组，布隆过滤器数据集合，黑名单一旦加入不可删除（或者说暂不支持删除亦或者说极难删除）。
    private static final int HASHCODEMETHODNUMBER = HashCodeLib.getHashcodemethodnumber();//HashCodeLib提供的hash函数个数
    private static final int NUMBER;//使用的hash函数个数,此值应该根据用户输入的样本量和失误率自动生成
    private static final int MOVE;//生成对应的内存空间的数组大小的位运算偏移量。

    //定义两个异常
    private static HashCodeMethodNumberOutOfException numberOutOfException = new HashCodeMethodNumberOutOfException();
    private static NumberFormatException numberformatexception = new NumberFormatException();

    /**
     * 静态代码块，从配置文件中获取
     */

    static {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        ClassLoader classLoader = BloomFilter.class.getClassLoader();
        logger.info(classLoader);
        InputStream in = classLoader.getResourceAsStream("config/config.properties");
        // 使用properties对象加载输入流
        String numberStr = null;
        String memoryStr = numberStr;

        int move = -1;
        try {
            properties.load(in);
            memoryStr = properties.getProperty("memory");
            numberStr = properties.getProperty("number");
            if ("512MB".equals(memoryStr)) {
                move = 27;
            } else if ("256MB".equals(memoryStr)) {
                move = 26;
            } else if ("1MB".equals(memoryStr)) {

                move = 18;
            } else {
                throw numberformatexception;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        MOVE = move;
        NUMBER = Integer.parseInt(numberStr);
        int size = 1 << MOVE;
        MEMORY = new int[size];
        logger.info(classLoader);
    }

    /**
     * 构造器私有
     */
    private BloomFilter() {

    }

    /**
     * 将对应输入的i插入到位图memory中
     *应该是private属性，测试用时，可以放开成public进行指定i的插入测试。
     * @param i 输入的hashcode值
     */
    public static void insert(int i) {
        //索引下标   （生成索引下边的具体步骤请移步MathTest::getIndex）
        //int index = ((((-1) >>> (32 - MOVE)) << 5) & (i >>> 5 << 5)) >>> 5;
        int index = (-1 >>> 32 - MOVE << 5 & i ) >>> 5;
        //获取i对应数组元素中的位下标（这里的计算方式和HashMap获取元素在数组的位置的方式一致）
        int code = i & 31;
        //将数组元素中对应位置为1
        MEMORY[index] = MEMORY[index] | (1 << code);
    }

    /**
     * 对外的insert方法，
     * 调用NUMBER次对内的insert()方法，将此key对应的所有标志插入到memory中
     * @param key
     * @return
     */
    public static boolean insertKey(String key) {

        try {
            for (int i = 0; i <= NUMBER; i++) {
                Integer integer = HashCodeLib.choiceHashMethod(key, i);
                logger.trace(i);
                logger.trace("integer = " + integer);
                insert(integer);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 同步代码块包裹的插入方法
     * 简单对insertKey进行了包裹
     * 应该还可以优化
     * @param key
     * @return
     */
    public static boolean insertKeySecurity(String key){
        synchronized (BloomFilter.class){
           return insertKey(key);
        }
    }

    /**
     * 针对特定的输入i进行检测
     * 检测对应位图上是否置1，
     * @param i
     * @return
     */
    private static boolean check(int i) {
        int index = (-1 >>> 32 - MOVE << 5 & i >>> 5 << 5) >>> 5;//获取数组的位置下标
        int code = i & 31;//截取i的后五位信息（也就是此i对应MEMERY元素的哪个位）
        int i1 = MEMORY[index] >>> code;//将i对应的位右移code位将i对应的位移动到最低位
        int i2 = i1 & 1;//将i1与1进行与操作获取此i是否在MEMORY位图中。
        return i2 == 1;

        //五步合并后，编译器化简的最终结果，这结果神仙应该也不知道这是在干啥吧。
//              return  1==((MEMORY[(-1 >>> 32 - MOVE << 5 & i >>> 5 << 5) >>> 5] >>> (i & 31))&1);
    }

    /**
     * 对外的check方法
     * 调用NUMBER次对内的check()方法，检查此key是否在名单里
     * 一旦某次check()返回位false则可确保
     * @param key
     * @return
     */
    public static boolean checkKey(String key) {

        for (int i = 0; i < NUMBER; i++) {
            Integer code = HashCodeLib.choiceHashMethod(key, i);
            if (!check(code))
                return false;
        }

        return true;
    }


}

