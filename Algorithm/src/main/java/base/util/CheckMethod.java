package base.util;

import base.exception.EmptyError;
import base.exception.SortErrorExption;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * @author shxl
 * @data 2022/5/27 22:55
 **/
public class CheckMethod {


    private static Method getMethod(Class sortClass,String method){

        Method m = null;
        try {
            m = sortClass.getMethod(method, int[].class);
        } catch (Exception e) {
            try {
                m = sortClass.getDeclaredMethod(method, int[].class);//获取私有函数
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            System.out.println(sortClass.getTypeName()+"."+method+" is not public");
            m.setAccessible(true);//设置方法可以强制执行
        }
        return m;

    }

    /**
     * 默认1,000次边长对照
     * @param sortClass
     * @param method
     */
    public static void check(Class sortClass ,String method) {
        check(sortClass,method,10000,10000,null);//1w次，1w变长数组检查
        check(sortClass,method,100,10,null);//100次，10变长数组检查
        try {
            check(sortClass, method, 0);//一次空数组检查
        } catch (Exception e) {
            throw new EmptyError();
        }



    }

    /**
     * 默认对一次，可指定数组长度
     * @param method
     * @param arrayLength
     */
    public static void check(Class sortClass,String method,Integer arrayLength) {
        check( sortClass,method,null,null,arrayLength);

    }

    /**
     * 可指定对照次数，和边长最大数组
     * @param sortClass
     * @param method
     * @param count
     * @param lengthMax
     */
    public static void check(Class sortClass,String method,Integer count,Integer lengthMax) {
        check( sortClass,method,count,lengthMax,null);

    }


    /**
     * 对内： 五参数接口 根据提供排序类的静态方法执行对照，正确控制台输出right 错误控制台输出error 并打印错误数组
     * @param sortClass 排序类
     * @param method 排序静态方法
     * @param count 执行对照的次数，没有默认100次
     * @param lengthMax 执行对照的数组最大长度 没有默认100
     * @param arrayLength 数组固定长度，与 lengthMax 冲突，所以对外提供的接口，这两个只需要提供一个即可。
     *
     *
     *                    标准的check方法应该是只需要两个参数：次数、长度。
     */

    private static Boolean check(Class sortClass, String method, Integer count, Integer lengthMax, Integer arrayLength) {
        if (count == null) {//不指定对数次数，默认一次
            count = 1;
        }
        if (lengthMax == null) {//不指定变长范围默认100
            lengthMax = 100;
        }
        try {
            Random random = new Random();
            Method m = getMethod(sortClass, method);
            for (int countIndex = 0; countIndex < count; countIndex++) {
                int length = arrayLength == null?random.nextInt(lengthMax) + 1:arrayLength;//如果提供了数组长度则直接使用数组长度，如果没有提供长度则
                int[] a = new int[length];
                for (int j = 0; j < length; j++) {
                    a[j] = random.nextInt(10000);
                }
                int[] ints = Arrays.copyOf(a, a.length);
                int[] errorArrays = Arrays.copyOf(a, a.length);

                Arrays.sort(a);
                try {
                    m.invoke(sortClass, ints);
                } catch (Exception e) {
                    System.out.println("error:\n"+Arrays.toString(errorArrays));
                    e.printStackTrace();
                    return false;
                }
                for (int index = 0; index < length; index++) {
                    if (!(ints[index] == a[index])) {
                        System.out.println("error:\n"+Arrays.toString(errorArrays));

                        return false;
                    }
                }
            }
            System.out.println("right");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }






}
