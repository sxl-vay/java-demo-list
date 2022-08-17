public class ATest {
    public static void main(String[] args) {
        test("mm");
    }

    public static void test(String s) {

        assert s.contains("a");
        System.out.println("s = " + s);
    }

}
