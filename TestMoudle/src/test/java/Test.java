import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shxl
 * @data 2022/7/5 21:23
 *
 **/
public class Test {
    public static void main(String[] args) {
        String m = "10.200.32.43asdfa";

        Pattern compile = Pattern.compile("\\W++");
        Matcher matcher = compile.matcher(m);
        while (matcher.find()) {
            String group = matcher.group(2);
            System.out.println("group = " + group);
        }
        System.out.println("group = " + m);


    }

    private static String ustartToCn(final String str) {

        StringBuilder sb = new StringBuilder().append("0x")

                .append(str.substring(2, 6));

        Integer codeInteger = Integer.decode(sb.toString());

        int code = codeInteger.intValue();

        char c = (char) code;

        return String.valueOf(c);

    }

}
