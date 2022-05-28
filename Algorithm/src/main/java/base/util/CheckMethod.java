package base.util;

import base.exception.EmptyError;
import base.exception.SortErrorExption;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * @author shxl
 * @data 2022/5/27 22:55
 **/
public class CheckMethod {


    private static Method getMethod(Class sortClass, String method) {

        Method m = null;
        try {
            m = sortClass.getMethod(method, int[].class);
        } catch (Exception e) {
            try {
                m = sortClass.getDeclaredMethod(method, int[].class);//获取私有函数
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            System.out.println(sortClass.getTypeName() + "." + method + " is not public");//TODO
            m.setAccessible(true);//设置方法可以强制执行
        }
        return m;

    }

    /**
     * 默认1,000次边长对照
     *
     * @param sortClass
     * @param method
     */
    public static void check(Class sortClass, String method) {
        try {
            check(sortClass, method, 10000, 10000, null);//1w次，1w变长数组检查
        } catch (SortErrorExption errorExption) {
            errorExption.printStackTrace();
        }
        try {
            check(sortClass, method, 100, 10, null);//100次，10变长数组检查
        } catch (SortErrorExption errorExption) {
            errorExption.printStackTrace();
        }
        try {
            check(sortClass, method, 0);//一次空数组检查
        } catch (Exception e) {
            throw new EmptyError();
        }



    }

    /**
     * 默认对一次，可指定数组长度
     *
     * @param method
     * @param arrayLength
     */
    public static void check(Class sortClass, String method, Integer arrayLength)  {

        try {
            check(sortClass, method, 1, null, arrayLength);
        } catch (SortErrorExption errorExption) {
            errorExption.printStackTrace();
        }

    }

    /**
     * 可指定对照次数，和最大数组
     *
     * @param sortClass 对比类
     * @param method 对比方法
     * @param count 对比次数
     * @param lengthMaxOrArrayLength 正数数组变长最大值，负数指定数组长度
     */
    public static void check(Class sortClass, String method, Integer count, Integer lengthMaxOrArrayLength)  {

        try {
            if (lengthMaxOrArrayLength >= 0) {
                check(sortClass, method, count, lengthMaxOrArrayLength, null);
            } else {
                check(sortClass,method,count,null,Math.abs(lengthMaxOrArrayLength));
            }
        } catch (SortErrorExption errorExption) {
            errorExption.printStackTrace();
        }

    }

    private static final Integer RANDOM_MAX = 100;
    private static final Integer COUNT = 10000;
    private static final Integer ARRAY_LENGTH = 100;
    /**
     * 对内： 五参数接口 根据提供排序类的静态方法执行对照，正确控制台输出right 错误控制台输出error 并打印错误数组
     *
     * @param sortClass 排序类
     * @param method 排序静态方法
     * @param count 执行对照的次数，没有默认100次
     * @param lengthMax 执行对照的数组最大长度 没有默认100
     * @param arrayLength 数组固定长度，与 lengthMax 冲突，所以对外提供的接口，这两个只需要提供一个即可。
     * <p>
     * <p>
     * 标准的check方法应该是只需要两个参数：次数、长度。
     */



    private static Boolean check(Class sortClass, String method, Integer count, Integer lengthMax, Integer arrayLength) throws SortErrorExption {
        Random random = new Random();
        count = count == null ? 1 : count;
        for (int i = 0; i < count; i++) {
            if (lengthMax == null) {
                arrayLength = arrayLength == null ? random.nextInt(ARRAY_LENGTH) : arrayLength;
            } else {
                arrayLength = lengthMax;
            }
            sort(sortClass, method, arrayLength, RANDOM_MAX);
        }
        return true;

    }


    /**
     * 指定次数和数组长度，以及随机数范围的排序算法
     * 方法不必有返回值，如果方法存在问题会直接抛出异常
     * 执行结束，代表方法正确。
     *
     * @param sortClass
     * @param method
     * @param length
     * @param randomMax
     * @return
     */
    private static void sort(Class sortClass, String method, Integer length, Integer randomMax) throws SortErrorExption {
        Random random = new Random();

        Method m = getMethod(sortClass, method);
            int[] a = new int[length];
            for (int integer = 0; integer < length; integer++) {
                a[integer] = random.nextInt(randomMax);
            }
            int[] ints = Arrays.copyOf(a, a.length);
            int[] errorArrays = Arrays.copyOf(a, a.length);
            Arrays.sort(a);
            try {
                m.invoke(sortClass, ints);
            } catch (Exception e) {
                throw new SortErrorExption(Arrays.toString(errorArrays));
            }
            Boolean aBoolean = checkArr(ints, a);//query：这里的方法是否需要抽离
            if (!aBoolean) {
                throw new SortErrorExption(Arrays.toString(errorArrays));
            }
    }

    /**
     * 对比两个数组是否相等
     *
     * @param b
     * @param a
     * @return
     */
    private static Boolean checkArr(int[] b, int[] a) {
        int length = a.length;
        if (!(b.length == length)) {
            return false;
        }
        for (int index = 0; index < length; index++) {
            if (!(b[index] == a[index])) {
                return false;
            }
        }

        return true;
    }


}
