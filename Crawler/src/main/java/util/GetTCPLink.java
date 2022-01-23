
package util;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 根据数据的url和cokkie
 */
public class GetTCPLink {
    private String url;
    private String cookie;
    private Map<String,String> headers;

    public GetTCPLink(String url, String cookie) {
        this.url = url;
        this.cookie = cookie;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    private HttpURLConnection getConnection() throws Exception {

        URL realUrl = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
        connection.setRequestProperty("accept", "*/*");
        //  connection.setRequestProperty("__random__", "1632967249843");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.setRequestProperty("cookie", this.cookie);
        if (this.headers!=null)
        this.headers.forEach((k,v)->{
            connection.setRequestProperty(k,v);
        });
        return connection;
    }

    public Boolean downloadPic(String name,String dir) throws Exception {

        HttpURLConnection connection = getConnection();
        File file = new File(dir + name + ".jpg");
        boolean exists = file.exists();
        if (!exists) {
        }

        if (connection.getResponseCode() == 200) {
            InputStream is = connection.getInputStream();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            //10MB的缓存
            byte[] buffer = new byte[10485760];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

            bos.close();
            is.close();
            //转换成json数据处理
            // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
            //JSONObject jsonArray = getJsonString(jsonString, comefrom);
            // return jsonArray;
            return true;
        }


        return false;
    }

    public JSONObject getHttpJson( int comefrom) throws Exception {
        try {
            HttpURLConnection connection = getConnection();

            //请求成功
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //10MB的缓存
                byte[] buffer = new byte[10485760];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String jsonString = baos.toString();
                baos.close();
                is.close();
                //转换成json数据处理
                // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
                JSONObject jsonArray = getJsonString(jsonString, comefrom);
                return jsonArray;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private JSONObject getJsonString(String str, int comefrom)  {
        JSONObject jo = null;
        if (comefrom == 1) {
            try {

                return new JSONObject(str);
            } catch (JSONException e){
                str = "{ \"str\":"+"notjson}";
                System.out.println(str);
                return  new JSONObject(str);
            }
        } else if (comefrom == 2) {
            int indexStart = 0;
            //字符处理
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    indexStart = i;
                    break;
                }
            }
            String strNew = "";
            //分割字符串
            for (int i = indexStart + 1; i < str.length() - 1; i++) {
                strNew += str.charAt(i);
            }
            return new JSONObject(strNew);
        }
        return jo;
    }

}
