package top.boking;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;


/**
 * 说明
 * 修改时
 * 类名要与文件名保持一致
 * class文件存放位置与路径保持一致。
 * 请把编译后的class文件，放在对应的目录中才能生效
 * 注意 同一路径下java名不能相同。
 *
 * @author Administrator
 */
public class AddTest {


    public static void main(String[] args) {
        AddTest addTest = new AddTest();
        addTest.doAction();
    }

    /**
     * restful接口调用案例
     * 以getModeDataPageList为例
     */
    public void doAction() {

        CloseableHttpResponse response;// 响应类,
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://oauat.tigermed.net/api/cube/restful/interface/saveOrUpdateModeData/ProjectCreateOrUpdate");


        //当前日期
        String currentDate = getCurrentDate();
        //当前时间
        String currentTime = getCurrentTime();
        //获取时间戳
        String currentTimeTamp = getTimestamp();

        Map<String, Map> params = new HashMap<>();


        //header
        //Map header = new HashMap<>();

        //系统标识
        //密码
        String systemid = "oaModeInfo";
        String d_password = "1";
        //封装header里的参数

        Map paramDatajson = new HashMap<>();
        JSONArray datas = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject operationinfo = new JSONObject();
        operationinfo.put("operationDate", getCurrentDate());
        operationinfo.put("operator", 10003857);
        operationinfo.put("operationTime", getCurrentTime());
        data.put("operationinfo", operationinfo);

        JSONObject header = new JSONObject();

        header.put("systemid", systemid);
        header.put("currentDateTime", currentTimeTamp);
        String md5Source = systemid + d_password + currentTimeTamp;
        String md5OfStr = getMD5Str(md5Source).toLowerCase();
        header.put("Md5", md5OfStr);

        data.put("mainTable", getMainTable());


        data.put("detail1", getDetailTable());

        datas.add(data);
        paramDatajson.put("data", datas);
        paramDatajson.put("header", header);
        params.put("datajson", paramDatajson);

        //封装operationinfo参数
        System.out.println("===请求参数datajson===" + paramDatajson);
        params.put("datajson", paramDatajson);
        //装填参数
        List nvps = new ArrayList();
        if (params != null) {
            for (Map.Entry entry : params.entrySet()) {

                nvps.add(new BasicNameValuePair((String) entry.getKey(), JSONObject.toJSONString(entry.getValue())));
            }
        }
        try {
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            response = httpClient.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                //返回信息
                String resulString = EntityUtils.toString(response.getEntity());

                //todo这里处理返回信息

                System.out.println("成功" + resulString);


            } else {
                System.out.println("获取数据失败，请查看日志" + currentDate + " " + currentTime);
            }
        } catch (Exception e) {
            System.out.println("请求失败" + currentDate + " " + currentTime + "====errormsg:" + e.getMessage());
        }


    }




    public JSONObject getMainTable() {
        JSONObject mainTable = new JSONObject();


        mainTable.put("id" , "34901" );
        mainTable.put("stage" , "Preparation" );
        mainTable.put("phase_no" , "759" );
        mainTable.put("client_no" , "101767" );
        mainTable.put("phase_name" , "759" );
        mainTable.put("project_no" , 122005185 );
        mainTable.put("study_title" , "NA" );
        mainTable.put("basic_detail" , "basic_detail" );
        mainTable.put("project_code" , "DEMO-20220502-TEST" );
        mainTable.put("project_name" , "DEMO-20220502-测试客户" );
        mainTable.put("sub_phase_no" , "778" );
        mainTable.put("creation_date" , "2022-05-0217 ,41 ,09" );
        mainTable.put("indication_no" , "2581" );
        mainTable.put("ip_category_no" , 656 );
        mainTable.put("project_status" , "Open" );
        mainTable.put("sub_phase_name" , "778" );
        mainTable.put("indication_name" , "2581" );
        mainTable.put("project_type_no" , 1 );
        mainTable.put("ip_category_name" , 656 );
        mainTable.put("last_update_date" , "2022-05-0710 ,51 ,28" );
        mainTable.put("project_language" , "Chinese" );
        mainTable.put("classification_no" , 18 );
        mainTable.put("project_type_name" , 1 );
        mainTable.put("signed_company_no" , "100" );
        mainTable.put("study_location_no" , "CHN" );
        mainTable.put("classification_name" , 18 );
        mainTable.put("company_chinesename" , "测试客户03" );
        mainTable.put("company_englishname" , "testname03" );
        mainTable.put("signed_company_name" , "100" );
        mainTable.put("study_location_name" , "CHN" );
        mainTable.put("study_location_type" , "SingleRegion" );
        mainTable.put("therapeutic_area_no" , "17,23" );
        mainTable.put("therapeutic_area_name" , "17,23" );
        mainTable.put("main_signed_company_no" , "100" );
        mainTable.put("ownership_personnel_no" , 10003857 );
        mainTable.put("ownership_personnel_name" , 10003857 );

        mainTable.put("dxwb", "loaclhost");

        return mainTable;
    }

    public JSONArray getDetailTable() {
        JSONArray deatilTable = new JSONArray();
        JSONObject deatil = new JSONObject();
        JSONObject action = new JSONObject();
        action.put("action","SaveOrUpdate");
        deatil.put("operate",action);




        JSONObject data = new JSONObject();

        data.put("type" , 1);
        data.put("enabled" , true);
        data.put("detailKey" , "122005185-1-2571756");
        data.put("personnel_no" , 10003857);
        data.put("personnel_name" , 10003857);
        data.put("personnel_email" , "yingjie.fu@tigermedgrp.com");
        data.put("personnel_phone" , "17857021392");
        data.put("personnel_status" , "Enable");
        data.put("personnel_type_no" , 819);
        data.put("personnel_username" , "yingjie.fu@tigermedgrp.com");
        data.put("personnel_type_name" , 819);
        data.put("personnel_englishname" , "");
        data.put("personnel_organization" , "shxl_shxl");




        deatil.put("data",data);





        deatilTable.add(deatil);


        return deatilTable;


    }







    public String getMD5Str(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String getCurrentTime() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        String currenttime = (timestamp.toString()).substring(11, 13) + ":" + (timestamp.toString()).substring(14, 16) + ":"
                + (timestamp.toString()).substring(17, 19);
        return currenttime;
    }

    public static String getCurrentDate() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        String currentdate = (timestamp.toString()).substring(0, 4) + "-" + (timestamp.toString()).substring(5, 7) + "-"
                + (timestamp.toString()).substring(8, 10);
        return currentdate;
    }

    /**
     * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
     *
     * @return 当前日期时间
     */
    public static String getCurDateTime() {
        Date newdate = new Date();
        long datetime = newdate.getTime();
        Timestamp timestamp = new Timestamp(datetime);
        return (timestamp.toString()).substring(0, 19);
    }

    /**
     * 获取时间戳   格式如：19990101235959
     *
     * @return
     */
    public static String getTimestamp() {
        return getCurDateTime().replace("-", "").replace(":", "").replace(" ", "");
    }

    public static int getIntValue(String v, int def) {
        try {
            return Integer.parseInt(v);
        } catch (Exception ex) {
            return def;
        }
    }


    public static String null2String(Object s) {
        return s == null ? "" : s.toString();

    }




    public static String GetImageStr(String imgPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = imgPath;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }
}