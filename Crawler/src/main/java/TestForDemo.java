import org.json.JSONObject;
import util.GetTCPLink;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TestForDemo extends Thread {
    public static final String BASICSTR = "https://www.e-cology.com.cn/api/hrm/resource4formal/getResourceCard?operation=getResourceBaseView&id=";
    public static final String FIN = "&noLoadData=1&__random__=163297228148";
    public static final String COOKIE = "HWWAFSESID=6d25d7947b9372a235; HWWAFSESTIME=1634656460230; __clusterSessionCookieName=F6F8F7662455065BB749D09482324A0B; JSESSIONID=cabhfHANdXWqXdnnIhzYx; d84d30aa93dc46c0ab0ead7d371b12f5=WyI5MDE1ODMwMzUiXQ; ecology_JSessionid=cabhfHANdXWqXdnnIhzYx; __randcode__=b372231a-61fa-4472-9425-423e6a45a0c4; loginidweaver=56635; languageidweaver=7; xzutyzw=10B01455A027819B77A8BE282372ED4E802A18AEA229B76802545A0B57F9207580F2421742B179175FA6F796EB203445E28EEE10840C1EB51E66578EC493A3D0; rutixcd=af7d7ab6-47af-456c-b21e-c308e121ef7f";

    public static void main(String[] args) {
       String name = "C:\\Test\\";
       String filename = "";
        for (int i = 0; i < 300; i++) {
            filename+="郝";
            String real = name+filename;
            File file = new File(real);
            if (file.mkdirs()) {
                System.out.println("succeed:"+filename);
            } else {
                System.out.println("error"+i);
            }
        }




    }
    public static void in(String data){
        LinkedHashMap<Integer,Character> stack = new LinkedHashMap<>();

        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean b = chars[i] == '{' ;
            if (b){
                stack.put(i,chars[i]);

            }

        }


    }

}
