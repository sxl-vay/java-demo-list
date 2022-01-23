package top.boking.utils;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
//暂时将这个注解的作用域设置成runtime，如有必要再进行修改
@Retention(RetentionPolicy.RUNTIME)
/**
 * 本注解配合HsahLevel枚举类，补充HashCodeLib类库中的hashcodemethod的信息
 */
public @interface HashType {
    HashLevel value() default HashLevel.NORMAL;

}

/**
 * 配合HashType注解
 * 标记HashCodeLib基础类库的各种hash的性能等级
 * <p>
 * 分为四个等级默认是NORMAL能级
 * HIGH
 * MIDDLE
 * NORMAL
 * LOW
 */
enum HashLevel {

    HIGH, MIDDLE, NORMAL, LOW
}

