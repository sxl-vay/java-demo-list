
package util;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * 根据数据的url和cokkie
 */
public class GetTCPLink2 {
    private String url;
    private String cookie;
    private Map<String,String> headers;

    public GetTCPLink2(String url, String cookie) {
        this.url = url;
        this.cookie = cookie;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    private HttpURLConnection getConnection() throws Exception {

        URL realUrl = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.setRequestProperty("Accept"," text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        connection.setRequestProperty("Accept-Encoding"," gzip, deflate, br");
        connection.setRequestProperty("Cache-Control", "max-age=0");





        connection.setRequestProperty("cookie", this.cookie);
        if (this.headers!=null)
        this.headers.forEach((k,v)->{
            connection.setRequestProperty(k,v);
        });
        return connection;
    }



    public String getHttpJson() throws Exception {
        String rt = "";
        try {
            HttpURLConnection connection = getConnection();

            //请求成功
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //10MB的缓存
                byte[] buffer = new byte[10485760];
                int len = 0;
                FileOutputStream fos = new FileOutputStream(new File("C:/Data/b.txt"));
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                String jsonString = baos.toString("UTF-8");

                return jsonString;
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
