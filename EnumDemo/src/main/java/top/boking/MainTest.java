package top.boking;

public class MainTest {
    public static void main(String[] args) {
        //枚举类方法列举
        //1.通过字符串值获取对象
        Season spring = Season.valueOf("SPRING");
        System.out.println(spring.toString());
        //2.获取当前类的所有枚举对象的数组
        Season[] values = Season.values();
        for (Season value : values) {
            System.out.println("value = " + value);
        }

    }
}
