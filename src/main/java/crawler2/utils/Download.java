package crawler2.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Download {
    public static String download(String url,HashMap<String,String> headers) throws Exception {
        //1.创建一个httpclient,相当于打开一个浏览器
        SSLClient httpclient = new SSLClient();

        RequestConfig config = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000).build();
        //2.创建get请求 相当于在浏览器输入URL
        HttpGet get = new HttpGet(url);
        //两组必要的请求头参数 Cookie User-Agent
        //get.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        //get.addHeader("Accept-Encoding","gzip, deflate");
        //get.addHeader("Accept-Language","h-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        get.addHeader("Cookie",headers.get("Cookie"));
        get.addHeader("User-Agent",headers.get("User-Agent"));
        CloseableHttpResponse response=null;
        String html=null;
        try {
            response = httpclient.execute(get);
            //4.判断响应状态，进行相应处理
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                html = EntityUtils.toString(entity,"GBK");
            }
            else{

            }
            //5.关闭资源
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally{
            response.close();
            httpclient.close();
        }
        return html;
    }
    public String download(String url) throws Exception {
        //1.创建一个httpclient,相当于打开一个浏览器
        SSLClient httpclient = new SSLClient();

        RequestConfig config = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000).build();
        //2.创建get请求 相当于在浏览器输入URL
        HttpGet get = new HttpGet(url);
        //get.addHeader("Cookie",headers.get("Cookie"));
        //get.addHeader("User-Agent",headers.get("User-Agent"));
        //3.执行get请求,相当于输入网址后按回车键
        CloseableHttpResponse response=null;
        String html=null;
        try {
            response = httpclient.execute(get);
            //4.判断响应状态，进行相应处理
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                html = EntityUtils.toString(entity,"GBK");
            }
            else{

            }
            //5.关闭资源
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally{
            response.close();
            httpclient.close();
        }
        return html;
    }
}
