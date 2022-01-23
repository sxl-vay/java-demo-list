package top.boking.bloom;

import org.apache.log4j.Logger;
import top.boking.exception.HashCodeMethodNumberOutOfException;
import top.boking.exception.NoPropertiesException;
import top.boking.utils.HashCodeLib;
import top.boking.utils.MemoryUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 采用long型数组存储元素
 */
public class BloomLongForTest {

    private BloomLongForTest() {

    }

    private static Logger logger = Logger.getLogger(BloomFilter.class);//log4j日志对象
    private static final long[] MEMORY;//位图数组，布隆过滤器数据集合，黑名单一旦加入不可删除（或者说暂不支持删除亦或者说极难删除）。
    private static final int HASHCODEMETHODNUMBER ;//HashCodeLib提供的hash函数个数
    private static final int SAVE_SIZE;//生成对应的内存空间的数组大小的保留位数（不太好理解，或许有更好的方式实现）。
    //非不可变属性一定得是私有的。

    private static long nowDataNumber = 0;//当前的实际样本量
    //公有参数一定得是final不可变的，这里的公有参数可以全部设计成私有，然后提供get方法。
    public static final long NUMBER;//样本个数
    public static final double MISSPROBABILITY;//需求的失误率
    public static final double REALMISSPROBABILITY;//真实失误率
    public static final String REALSIZE;//实际开辟内存空间(可视化的字符串类型)
    public static final int K_USEHASHCODENUMBER;//使用hash函数个数

    public static final long REALMEMORYBIT;//实际开辟内存空间（位）
    //定义异常
    private static HashCodeMethodNumberOutOfException numberOutOfException = new HashCodeMethodNumberOutOfException();
    private static NumberFormatException numberformatexception = new NumberFormatException();

    private static NoPropertiesException noPropertiesException = new NoPropertiesException();


    /**
     * 静态代码块，从配置文件中获取
     * 所有的计算性质都放置在静态代码块中实现，当类初始化时一次性计算生成，避免重复计算浪费性能
     */
    static {
        HASHCODEMETHODNUMBER= HashCodeLib.getHashcodemethodnumber();
        Map<String, Object> npMap = initProperties();
        NUMBER =(Long) npMap.get("NUMBER");
        MISSPROBABILITY =(Double) npMap.get("MISSPROBABILITY");
        long m = getNeedMemorySize(NUMBER, MISSPROBABILITY);
        int k = getHashCodeMethodNumber(NUMBER, m);
        REALMEMORYBIT = getRealMemorySize(NUMBER, MISSPROBABILITY);
        REALMISSPROBABILITY = getRealMissProbability(NUMBER, REALMEMORYBIT, k);
        REALSIZE = MemoryUtils.inBitOutSize(REALMEMORYBIT);
        int save_size = 0;
        long flag = REALMEMORYBIT;
        while (((flag=flag>>1)&1)==0){
            save_size++;
        }
        SAVE_SIZE = save_size -5;
        int size = 1 << SAVE_SIZE;
        MEMORY = new long[size];
        K_USEHASHCODENUMBER = getHashCodeMethodNumber(NUMBER, REALMEMORYBIT);

        logger.info("MOVE:"+ SAVE_SIZE);
        logger.info("realMemorySize = " + REALMEMORYBIT);
        logger.info("实际开辟的空间："+REALSIZE);
        logger.info("实际使用的hashcode个数"+k);
        logger.info("m"+m);

    }

    public static void init(){

    }

    /**
     * read config properties file ,and return params map
     * @return
     */
    public static Map<String,Object> initProperties(){
        HashMap<String, Object> map = new HashMap<>();
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        ClassLoader classLoader = BloomFilter.class.getClassLoader();
        logger.info(classLoader);
        InputStream in = classLoader.getResourceAsStream("config/config.properties");
        // 使用properties对象加载输入流
        String n = null;
        String memoryStr = n;
        MemorySize mb = null;
        String p = null;
        try {
            properties.load(in);
            //memoryStr = properties.getProperty("memory");
            n = properties.getProperty("number");
            p = properties.getProperty("missProbability");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (n == null || p == null) {
            throw noPropertiesException;
        }
        map.put("NUMBER",Long.parseLong(n));
        map.put("MISSPROBABILITY",Double.parseDouble(p));
        return map;
    }

    /**
     * 将对应输入的i插入到位图memory中
     * 应该是private属性，测试用时，可以放开成public进行指定i的插入测试。
     *
     * @param i 输入的hashcode值
     */
    public static void insert(int i) {
        //索引下标   （生成索引下边的具体步骤请移步MathTest::getIndex）

        int index = (-1 >>> 32 - SAVE_SIZE << 6 & i) >>> 6;
        //获取i对应数组元素中的位下标（这里的计算方式和HashMap获取元素在数组的位置的方式一致）
        int code = i & 63;
        //将数组元素中对应位置为1
        MEMORY[index] = MEMORY[index] | (1 << code);
    }

    /**
     * 对外的insert方法，
     * 调用NUMBER次对内的insert()方法，将此key对应的所有标志插入到memory中
     *
     * @param key
     * @return
     */
    public static boolean insertKey(String key) {
        if (nowDataNumber>= NUMBER){
            logger.warn("警告：插入的样本已经超过设计值NUMBER，继续插入失误率可能会超出预设范围");
        }
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
        nowDataNumber++;//添加成功后将实际样本量加1。
        return true;
    }

    /**
     * 同步代码块包裹的插入方法方法
     * 简单对insertKey进行了包裹
     * 应该还可以优化
     *
     * @param key
     * @return
     */
    public static boolean insertKeySecurity(String key) {
        synchronized (BloomFilter.class) {
            return insertKey(key);
        }
    }

    /**
     * 针对特定的输入i进行检测
     * 检测对应位图上是否置1，
     *
     * @param i
     * @return
     */
    public static boolean check(int i) {
        int index = (-1 >>> 32 - SAVE_SIZE << 6 & i) >>> 6;//获取数组的位置下标
        int code = i & 63;//截取i的后五位信息（也就是此i对应MEMERY元素的哪个位）
        long i1 = MEMORY[index] >>> code;//将i对应的位右移code位将i对应的位移动到最低位
        long i2 = i1 & 1;//将i1与1进行与操作获取此i是否在MEMORY位图中。
        return i2 == 1;

        //五步合并后，编译器化简的最终结果，这结果神仙应该也不知道这是在干啥吧。
//              return  1==((MEMORY[(-1 >>> 32 - MOVE << 6 & i ) >>> 6] >>> (i & 63))&1);
    }

    /**
     * 对外的check方法
     * 调用NUMBER次对内的check()方法，检查此key是否在名单里
     * 一旦某次check()返回位false则可确保
     *
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

    //以下是计算方法

    /**
     * 根据样本量和失误率计算所需空间的公式：
     * m = - [n*ln(p)]/[(ln2)^2]
     *
     * @param n 样本量（个）
     * @param p 失误率（小数）
     * @return 理论所需空间（bit）
     */
    public static long getNeedMemorySize(long n, double p) {
        double high = n * Math.log(p);
        double low = Math.log(2) * Math.log(2);
        long m = (long) -(high / low);//这里需要向上取整所以number要+1，但是进行
        return m + 1;
        //long number = m +1-1;
        /*long flag = Long.MIN_VALUE;
        while ((flag & m) == 0) {
            flag >>>= 1;
        }
        return flag<<1;*/

    }

    /**
     * 计算出实际分配的内存空间（位）
     * @param n 样本量
     * @param p 所需失误率
     * @return 实际分配的内存空间（位）
     */
    public static long getRealMemorySize(long n, double p) {
        long m = getNeedMemorySize(n, p) - 1;

        long flag = Long.MIN_VALUE;
        while ((flag & m) == 0) {
            flag >>>= 1;
        }
        return flag << 1;

    }

    /**
     * 根据样本量和理论所需空间计算所需的hash函数个数k
     *
     * @param n 样本量
     * @param m 理论所需空间
     * @return
     */
    public static int getHashCodeMethodNumber(long n, long m) {
        int k = (int) (Math.log(2) * (m / n));
        return k + 1;
    }

    /**
     * 计算实际的失误率
     * @param n 样本量
     * @param m 实际分配空间（位）
     * @param k 实际的hash函数个数
     * @return 真实失误率
     */
    public static double getRealMissProbability(long n, long m, int k) {
        float f = n * k;
        float e = f / m;
        return Math.pow((1 - Math.pow(Math.E, -e)), k);
    }

    /**
     * 返回当前实际的样本量。
     * @return
     */
    public static long getNowDataNumber(){
        return nowDataNumber;
    }

    public static double getNowMissProbability(){

        float f = nowDataNumber * K_USEHASHCODENUMBER;
        float e = f /REALMEMORYBIT;
        return Math.pow((1 - Math.pow(Math.E, -e)), REALMEMORYBIT);
    }


}
