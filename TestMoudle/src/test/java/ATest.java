import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class ATest {
    public static void main(String[] args) {
/*        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();*/

        String sql = "a=?=z?";
        String s1 = null2String("z\\z");
        try {

        } catch (StringIndexOutOfBoundsException e) {

        }
        int i = sql.indexOf("?");
        System.out.println("i = " + i);
    }
    public static String null2String(Object s) {
        return s == null ? "" : s.toString();

    }
}
