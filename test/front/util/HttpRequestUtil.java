package front.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 发起HTTP请求的Util类
 * @author terwergreen
 * @since 2015-6-20
 */
@SuppressWarnings({ "rawtypes" })
public class HttpRequestUtil {

    /**
     * 通过java发送 post请求访问URL并根据传递参数不同返回不同结果 
     * @param urlStr 		请求的URL
     * @param requestMap 	请求数据
     */
    public static String javaPost(String urlStr, Map<String, String> requestMap) {
    	long startTime = System.currentTimeMillis();
        System.out.println("requestUrl:" + urlStr);  
        System.out.println("requestMap:" + JSON.toJSONString(requestMap)); 
    	String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符  
        OutputStream out = null;
        InputStream in = null;
        BufferedReader reader = null;
        try {  
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn.setRequestProperty("User-Agent",  "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);  
  
            out = new DataOutputStream(conn.getOutputStream());  
            
            // text  
            if (requestMap != null) {  
                StringBuffer strBuf = new StringBuffer();  
                Iterator iter = requestMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");  
                    strBuf.append(inputValue);  
                }  
                out.write(strBuf.toString().getBytes());  
            }  
  
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();  
  
            // 读取返回数据  
            StringBuffer strBuf = new StringBuffer();  
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf.append(line).append("\n");  
            }  
            res = strBuf.toString();  
            System.out.println("Response： " + res);
            in.close();
            reader.close();  
            reader = null;  
        } catch (Exception e) {  
            System.out.println("Error occured during reqeust: " + urlStr);  
            e.printStackTrace();  
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}  
        System.out.println("Request total time: " + (System.currentTimeMillis() - startTime));
        return res;
    }
}
