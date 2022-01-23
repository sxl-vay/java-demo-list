package util;

import POJO.OptionsEntity;
import exception.CoordinateException;
import main.MainZero;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import main.MainTest;

/**
 * @Classname JsonHelper
 * @Description TODO
 * @Date 2022/1/22 18:13
 * @Created by shxl
 */
public class JsonAndMainHelper {
    private static Logger log = Logger.getLogger(JsonAndMainHelper.class.getClass());
    public static int START;
    public static int END;
    public static int countForRun;
    public static String dirForRun;
    public static Map<String, Object> enterInfoForRun;
    public static final int THREAD_COUNT = 10;
    public static final String BASICSTR = "https://www.e-cology.com.cn/api/hrm/resource4formal/getResourceCard?operation=getResourceBaseView&id=";
    public static final String FIN = "&noLoadData=1&__random__=163297228148";
    public static String COOKIE;

    public static void downloadPic4Thread(int start, int end) {
        JSONObject httpJson = null;
        String url = BASICSTR + start + FIN;
        GetTCPLink getJson = new GetTCPLink(url, COOKIE);
        try {
            httpJson = getJson.getHttpJson(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s = httpJson.toString();
        if ("{}".equals(s)) {
            return;
        }
        boolean b = JsonAndMainHelper.ifGetPic(s, enterInfoForRun);

        String name = JsonAndMainHelper.getInfo("姓名", s);
        String number = JsonAndMainHelper.getInfo("编号", s);
        String picSrc = JsonAndMainHelper.getInfo("照片", s);

        if (b) {
            GetTCPLink down = new GetTCPLink("https://www.e-cology.com.cn" + picSrc, JsonAndMainHelper.COOKIE);
            String fileName = number + "_" + (start + end) + "_" + name;
            try {
                down.downloadPic(fileName, dirForRun);
                log.info("download " + fileName + " succeed：");
                log.debug(Thread.currentThread().getName());


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void threadProcessing() {

        int number = END - START;
        countForRun = number / THREAD_COUNT;
        log.trace("countforRun:" + countForRun);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 15, 5, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        Map<Integer, Integer> countMap = new HashMap<>();
        MainZero mainTest = new MainZero();
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPoolExecutor.submit(mainTest);
        }
        threadPoolExecutor.shutdown();
    }



    public static void createDir() {
        START = (Integer) enterInfoForRun.get("start");
        END = (Integer) enterInfoForRun.get("end");
        StringBuilder dirBuiler = new StringBuilder();
        enterInfoForRun.forEach((k, v) -> {
            dirBuiler.append(v);
        });
        String dirStr = dirBuiler.toString();
        int length = dirStr.length();
        if (length>255){
            dirStr = dirStr.substring(0, 255);
            log.info("文件名称过长进行截取");
        }
        dirForRun = "C:\\泛微图片\\" + dirStr  ;
        File file = new File(dirForRun);
        if (file.mkdirs()) {
            log.info("创建文件夹" + dirForRun + "成功");
        } else {
            log.info("文件夹已存在");
        }
    }


    static {
        enterInfoForRun = new HashMap<>();
        Yaml yaml = new Yaml();
        OptionsEntity optionsEntity = yaml.loadAs(MainTest.class.getResourceAsStream("/prop/init.yml"), OptionsEntity.class);
        Map<String, Object> options = optionsEntity.getOptions();
        enterInfoForRun.putAll(options);
        Integer start = optionsEntity.getStart();
        Integer end = optionsEntity.getEnd();
        enterInfoForRun.put("start", start);
        enterInfoForRun.put("end", end);
        COOKIE = optionsEntity.getCookie();
    }


    public static void init() {
        log.info("init do:" + enterInfoForRun);
    }






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
