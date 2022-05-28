package main;

import POJO.OptionsEntity;
import exception.CoordinateException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;
import util.GetTCPLink2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainTest3 {

    public static final String BASICSTR = "https://www.e-cology.com.cn/customersystem/data/CustomerSystemRecordView_Base.jsp?id=$&isfromtab=true";
    public static String COOKIE = "HWWAFSESID=3dbff10f30ea31d5362; HWWAFSESTIME=1652755067504; JSESSIONID=cbaIyxxzLPLorf0iq4pdy; ecology_JSessionid=cbaIyxxzLPLorf0iq4pdy; loginidweaver=56635; languageidweaver=7; __randcode__=b2037840-0453-49e6-9463-164b1a8903b6; xzutyzw=182462E72CA1EA6B70E14AF562803231FD5B45A78A8BF548612E1EF8649234ED810F6C04F41A1D1314310AFE3670794E6CABC39E1352FA944AE16EBC8D31DF70; rutixcd=b296a61a-26fc-4910-b635-2c1f582aaa8a; __clusterSessionCookieName=880E70DAE90DC3821FFF0D7735541C1F; d84d30aa93dc46c0ab0ead7d371b12f5=WyI0MDA3ODM3NDYxIl0";
    private static int START;
    private static int END;
    private static int countForRun;
    private static String dirForRun;
    private static Map<String, Object> enterInfoForRun;
    private static final int THREAD_COUNT = 10;

    private static Logger log = Logger.getLogger(MainTest3.class.getClass());

    public static void main(String[] args) {
        downloadPic4Thread(1,1);
    }






    public static void downloadPic4Thread(int start, int end) {
        JSONObject httpJson = null;
        int indexOf = BASICSTR.indexOf("$");

       for (int i = 10000; i < 20000; i++) {
           String url = BASICSTR.substring(0,indexOf)+i+BASICSTR.substring(indexOf+1) ;
           GetTCPLink2 getJson = new GetTCPLink2(url, COOKIE);
           try {
               String returnstr = getJson.getHttpJson();
               String s = "<span id=dbVersionName name=dbVersionName>";
               int length = s.length();
               System.out.println("length = " + length);
               int is = returnstr.indexOf("<span id=dbVersionName name=dbVersionName>");
               String substring = returnstr.substring(is+42, is+52);
               System.out.println("substring = :" + substring);
               boolean mysql = substring.startsWith("DB2") || substring.startsWith("db2");
               String s1 = "i = " + i + "\t" + substring;
               System.out.println(s1);
               if (mysql) {
                   File file = new File("C:/Data/sql.txt");
                   FileOutputStream fileOutputStream = new FileOutputStream(file);
                   fileOutputStream.write(s1.getBytes(),0,s1.length());
                   return;
               }


           } catch (Exception e) {
               e.printStackTrace();
           }
        }


    }

    /**
     * 读取对应的yml配置文件，（本方法必须保证和键入的enterInfo对象格式完全一致）
     *
     * @return enterInfo的map对象
     */
    public static Map<String, Object> initYml() {
        Map<String, Object> caseMap = new HashMap<>();
        Yaml yaml = new Yaml();
        OptionsEntity optionsEntity = yaml.loadAs(MainTest3.class.getResourceAsStream("/prop/init.yml"), OptionsEntity.class);
        Map<String, Object> options = optionsEntity.getOptions();
        caseMap.putAll(options);
        Integer start = optionsEntity.getStart();
        Integer end = optionsEntity.getEnd();
        COOKIE = optionsEntity.getCookie();

        caseMap.put("start", start);
        caseMap.put("end", end);
        System.out.println(caseMap);
        return caseMap;

    }


    /**
     * 获取条件对象enterInfo（包含下载图片的条件值，以及爬取的人员起止坐标）
     * 两种方式
     * 1.读取yml文件形式：调用initYml（）
     * 2.键入方式：调用getEnterInfoByKeyboard()
     *
     * @return map集合
     */
    public static Map<String, Object> getEnterInfo() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> caseMap = new HashMap<>();
        System.out.println("是否读取yml:y/n");
        String ymlConfirmStr = scanner.nextLine();
        boolean innerFlag = true;
        if ("y".equalsIgnoreCase(ymlConfirmStr)) {
            caseMap = initYml();
            innerFlag = false;
        } else
            System.out.println("---------------录入信息-------------------");
        caseMap = getEnterInfoByKeyboard(innerFlag, caseMap);
        scanner.close();
        return caseMap;
    }


    /**
     * 循环读取键入信息，只有当符合条件后才跳出循环返回casemap
     * 值得注意的是：不管选不选择键入的方式本方法都会被调用，但是如果不是键入方式时，本方法的内部逻辑并不会被调用
     * 内部while循环的跳出并不是依靠修改flag为false，而是内部有个break方法。
     *
     * @param flag    是否进入循环
     * @param caseMap
     * @return
     */
    public static Map<String, Object> getEnterInfoByKeyboard(boolean flag, Map<String, Object> caseMap) {
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            confirmCoordinate(scanner, caseMap);
            System.out.println("性别：0男，1女，2不限");
            String sexIn = scanner.nextLine();
            System.out.println("地区：0代表不限，如果要限制城市请数据城市名空格分割");
            String area = scanner.nextLine();
            System.out.println("状态：多选，空格分割,0为不区分");
            String state = scanner.nextLine();
            System.out.println("分部，空格分割,0为不区分");
            String sub = scanner.nextLine();
            Object start = caseMap.get("start");
            Object end = caseMap.get("end");
            System.out.println("坐标区间：" + start + "-" + end);

            //以下是对信息进行判断，抽取成公共方法的意义并不大，暂时不抽取。
            //性别判断
            if ("".equals(sexIn)) {
                System.out.println("性别：不限");
                caseMap.put("sex", "不限");
            } else {
                try {
                    switch (Integer.parseInt(sexIn)) {
                        case 0:
                            System.out.println("性别：男");
                            caseMap.put("sex", "男");
                            break;
                        case 1:
                            System.out.println("性别：女");
                            caseMap.put("sex", "女");
                            break;
                        default:
                            System.out.println("性别：不限");
                            caseMap.put("sex", "不限");
                    }
                } catch (Exception e) {
                    System.out.println("请输入正确性别");
                }

            }
            //地区判断
            if (!"0".equals(area) && !"".equals(area)) {
                System.out.println("地区：" + area);
                caseMap.put("area", area);
            } else {
                System.out.println("地区：不限" + area);
            }
            //状态判断
            if (!"0".equals(state) && !"".equals(state)) {
                System.out.println("状态：" + state);
                caseMap.put("state", state);
            } else {
                System.out.println("状态：不限" + state);
            }
            //部门判断
            if (!"0".equals(sub) && !"".equals(sub)) {
                System.out.println("分部：" + sub);
                caseMap.put("sub", sub);
            } else {
                System.out.println("分部：不限" + sub);
            }
            System.out.println("\n\n是否确定：y/n");
            String conform = scanner.nextLine();
            if ("Y".equalsIgnoreCase(conform)) {
                break;
            }
            System.out.println("---------------重新录入-------------------");
        }
        scanner.close();
        return caseMap;
    }

    public static void confirmCoordinate(Scanner scanner, Map<String, Object> caseMap) {
        boolean confirm = false;

        System.out.println("请录入坐标");
        while (!confirm) {
            String enter = scanner.nextLine();
            String[] s = enter.split(" ");
            if (s.length != 2) {
                System.out.println("坐标解析异常，请重新录入");
                continue;
            }
            int start = 0;
            int end = 0;
            try {
                start = Integer.parseInt(s[0]);
                end = Integer.parseInt(s[1]);
                caseMap.put("start", start);
                caseMap.put("end", end);
                if (start >= end)
                    throw new CoordinateException();
                confirm = true;
            } catch (CoordinateException e) {
                System.out.println("坐标区间至少包含一个值");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("坐标解析异常，请重新录入");
                e.printStackTrace();
            }


        }


    }

    //Main Helper Methods

    /**
     * 根据获取的enterInfo和当前json对比确定当前人员是否获取图片
     *
     * @param s         json对应的string字符串
     * @param enterInfo 通过键入或者是yml配置获取的enterInfo信息
     * @return 是否获取图片
     */
    public static boolean ifGetPic(String s, Map<String, Object> enterInfo) {

        String sex = getInfo("性别", s);
        String sexIn = (String) enterInfo.get("sex");

        boolean b4 = confirmField((String) enterInfo.get("area"), getInfo("办公地点", s)) &&
                confirmField((String) enterInfo.get("sex"), getInfo("性别", s)) &&
                confirmField((String) enterInfo.get("state"), getInfo("状态", s)) &&
                confirmField((String) enterInfo.get("sub"), getSub(s));

        return b4;
    }

    /**
     * 确认人员信息是否满足条件，多选形式的判断，
     * 历史原因此方法的enter入参本可以是List形式（这样更加优雅）但是使用空格分割的string更加方便
     *
     * @param enter      输入的多选条件
     * @param currentStr 人员JSON中的条件值
     * @return 是否满足
     */
    public static boolean confirmField(String enter, String currentStr) {
        if (enter == null) return true;
        String[] s1 = enter.split(" ");
        boolean areaFlog = false;
        for (String s2 : s1) {
            if (currentStr.equals(s2)) {
                areaFlog = true;
                break;
            }

        }
        return areaFlog;
    }

    //JSON helper methods


    /**
     * 根据返回接送格式可以通过这种普适方法获取信息
     *
     * @param in   要获取的人员信息
     * @param json 输入json
     * @return 返回in对应的人员信息
     */
    public static String getInfo(String in, String json) {
        String[] split = json.split(in);
        String[] split1 = split[1].split("\"");
        return split1[4];

    }

    /**
     * 获取分部信息  原理同getInfo，但是分部在json中的格式稍有不同，需要定制
     *
     * @param json
     * @return
     */
    public static String getSub(String json) {
        String[] split = json.split("组织");
        String[] split1 = split[1].split("\"");
        return split1[8].substring(2, 8);

    }

}
