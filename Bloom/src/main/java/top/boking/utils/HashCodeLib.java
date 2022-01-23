package top.boking.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author shxl
 * 哈希函数工具类库，集合了我自定义的各种hash函数
 */
public class HashCodeLib {
    private HashCodeLib() {
    }

    //hash当前类的函数个数
    private static final int HASHCODEMETHODNUMBER;

    private static final ArrayList<Method> methodList;//其实直接使用ArrayList就已经可以实现了。而且效率甚至更高

    /**
     * 通过反射获取当前类中的hash函数方法的个数，赋值给hashcodemethodnumber
     * （静态代码块编写，仅在类加载时执行一次，减少开销）
     */
    static {
        Method[] methods = HashCodeLib.class.getDeclaredMethods();
        methodList = new ArrayList<>();
        int count = 0;
        if(methods != null){
            for(Method method : methods){
                HashType annotation = method.getAnnotation(HashType.class);
                if(annotation == null)
                    continue;
                methodList.add(method);
            }
        }
       /* methodList.forEach((k)->{
            System.out.println(k);
        });*/
        HASHCODEMETHODNUMBER = methodList.size();
    }

    public static int getHashcodemethodnumber(){
        return HASHCODEMETHODNUMBER;
    }
    public static Integer choiceHashMethod(String key, int kind) {

        Method method = methodList.get(kind);
        Class stringClass = String.class;
        try {
            int sxl =(int) method.invoke(stringClass,key);
            return sxl;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        /*switch (kind) {
            case 0:
                return hashCodeADD(key);
            case 1:
                return hashCodeRotating(key);
            case 2:
                return hashCodeFNV(key);
            case 3:
                return hashCodeRS(key);
            case 4:
                return hashCodeFNVandADD(key);
            case 5:
                return hashCodeFNVandRS(key);
            case 6:
                return hashCodeFNVandRS(key);
            case 7:
                return hashCodeFNVandRS(key);
            case 8:
                return hashCodeFNVandRS(key);
            case 9:
                return hashCodeFNVandRS(key);
            case 10:
                return hashCodeFNVandRS(key);
            case 11:
                return hashCodeFNVandRS(key);
            case 12:
                return hashCodeFNVandRS(key);
            case 13:
                return hashCodeFNVandRS(key);
            case 14:
                return hashCodeFNVandRS(key);
            case 15:
                return hashCodeFNVandRS(key);
            case 16:
                return hashCodeFNVandRS(key);

        }*/
        return null;
    }

    /**
     * FNV的hash算法
     *
     * @param key
     * @return 返回32位hash函数值
     */
    @HashType(HashLevel.HIGH)
    public static int hashCodeFNV(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {

            hash = (hash ^ key.charAt(i)) * p;
            hash += hash << 13;
            hash ^= hash >> 7;
            hash += hash << 3;
            hash ^= hash >> 17;
            hash += hash << 5;
        }
        return hash;
    }

    /**
     * 加法型hash
     *
     * @param key hash输入值
     * @return
     */
    @HashType(HashLevel.NORMAL)
    public static int hashCodeADD(String key) {

        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return hash;

    }

    /**
     * 标准的旋转hash， 通过先移位，在进行各种位运算。 比如某个学校同一系的新生，学号前5位相同，最后2位不同，
     * 将最后2位旋转放置前两位，其余的右移。这样只要输入前两位，就可以快速查出学生的信息。
     * @param key hash key
     */
    @HashType(HashLevel.NORMAL)
    private static int hashCodeRotating(String key) {
        int hash;
        int i;
        for (hash = key.length(), i = 0; i < key.length(); i++) {
            hash = (hash<<4)^(hash>>28)^key.charAt(i);
        }
        return hash ;  //除以 一个prime的目的只是为了保证结果的范围
    }

    /**
     * 乘以一个不断该不变的数
     * @param key hash key
     * @return hash value
     */
    @HashType(HashLevel.NORMAL)
    public static int hashCodeRS(String key) {
        int b = 37855;
        int a = 63689;
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = hash * a + key.charAt(i);
            a = a * b;
        }
        return hash ;
    }



    @HashType(HashLevel.NORMAL)
    private static int hashCodeFNVandADD(String key){

        return hashCodeFNV(key)&hashCodeADD(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeFNVorADD(String key){
        return hashCodeFNV(key) | hashCodeADD(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeFNVxorADD(String key){
        return hashCodeFNV(key) ^ hashCodeADD(key);
    }


    @HashType(HashLevel.NORMAL)
    private static int hashCodeRotatingxorADD(String key){
        return hashCodeRotating(key) ^ hashCodeADD(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeRotatingorADD(String key){
        return hashCodeRotating(key) | hashCodeADD(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeRotatingandADD(String key){
        return hashCodeRotating(key)& hashCodeADD(key);
    }



    @HashType(HashLevel.NORMAL)
    private static int hashCodeRotatingorRS(String key){
        return hashCodeRotating(key) | hashCodeRS(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeRotatingandRS(String key){
        return hashCodeRotating(key) &  hashCodeRS(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeRotatingxorRS(String key){
        return hashCodeRotating(key) ^  hashCodeRS(key);
    }

    @HashType(HashLevel.NORMAL)
    private static int hashCodeFNVorRS(String key){
        return hashCodeFNV(key) | hashCodeRS(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeFNVandRS(String key){
        return hashCodeFNV(key) &  hashCodeRS(key);
    }
    @HashType(HashLevel.NORMAL)
    private static int hashCodeFNVxorRS(String key){
        return hashCodeFNV(key) ^  hashCodeRS(key);
    }




}
