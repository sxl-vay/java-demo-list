package main;

import POJO.OptionsEntity;
import exception.CoordinateException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;
import util.GetTCPLink2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainTest5 {

    public static final String BASICSTR = "https://www.e-cology.com.cn/customersystem/data/CustomerSystemRecordView_Base.jsp?id=$&isfromtab=true";
    public static String COOKIE = "HWWAFSESID=3dbff10f30ea31d5362; HWWAFSESTIME=1652755067504; JSESSIONID=cbaIyxxzLPLorf0iq4pdy; ecology_JSessionid=cbaIyxxzLPLorf0iq4pdy; loginidweaver=56635; languageidweaver=7; __randcode__=b2037840-0453-49e6-9463-164b1a8903b6; xzutyzw=182462E72CA1EA6B70E14AF562803231FD5B45A78A8BF548612E1EF8649234ED810F6C04F41A1D1314310AFE3670794E6CABC39E1352FA944AE16EBC8D31DF70; rutixcd=b296a61a-26fc-4910-b635-2c1f582aaa8a; __clusterSessionCookieName=880E70DAE90DC3821FFF0D7735541C1F; d84d30aa93dc46c0ab0ead7d371b12f5=WyI0MDA3ODM3NDYxIl0";
    private static int START;
    private static int END;
    private static int countForRun;
    private static String dirForRun;
    private static Map<String, Object> enterInfoForRun;
    private static final int THREAD_COUNT = 10;

    private static Logger log = Logger.getLogger(MainTest5.class.getClass());

    public static void main(String[] args) {
        HashMap<Object, Object> tableMap = new HashMap<>();

        tableMap.put("5401AB9C-030C-4927-B00D-FB6F6781979C",	"formid:-479$$$modename:履历材料电子原文");
        String o =(String) tableMap.get("5401AB9C-030C-4927-B00D-FB6F6781979C");
        String[] split = o.split("\\$\\$\\$");
        String s = Arrays.toString(split);

        System.out.println("s = " + s);

    }













}
