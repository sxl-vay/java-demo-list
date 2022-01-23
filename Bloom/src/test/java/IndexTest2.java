public class IndexTest2 {
    public static void main(String[] args) {
        int x = 37859376;
        int i = (x >>> 5);
        int i1 = i << 5;
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(x));
    }
}
