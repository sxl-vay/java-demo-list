package top.boking.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author shxl
 * @Date 2024/8/12 23:18
 * @Version 1.0
 */
public enum SelfEnum {
    SingleTon;

    public static void main(String[] args) throws Exception{
        Constructor<SelfEnum> declaredConstructor = SelfEnum.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        SelfEnum selfEnum = declaredConstructor.newInstance("xxx",1);
        System.out.println(selfEnum);
    }
}
