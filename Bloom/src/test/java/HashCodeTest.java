import top.boking.utils.HashCodeLib;

public class HashCodeTest {
    public static void main(String[] args) {
        Integer sxl = HashCodeLib.choiceHashMethod("sx1l", 1);
        System.out.println("sxl = " + sxl);
        Integer sxl1 = HashCodeLib.choiceHashMethod("sxdl", 1);
        System.out.println("sxl1 = " + sxl1);
        Integer sxl2 = HashCodeLib.choiceHashMethod("sxgl", 1);
        System.out.println("sxl2 = " + sxl2);
        Integer sxl3 = HashCodeLib.choiceHashMethod("sxdl", 1);
        System.out.println("sxl3 = " + sxl3);


    }
}
