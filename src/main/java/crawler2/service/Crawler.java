package crawler2.service;

import crawler2.entity.Picture;
import crawler2.utils.SSLClient;
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
import java.util.List;

//每个爬虫需要具备的最基本的两个功能 爬取数据 解析数据
//download和getContext download继承即可 getContext需要子类实现
public abstract class Crawler<T> {

    public String download(String url,HashMap<String,String> headers) throws Exception {

        //1.创建一个httpclient,相当于打开一个浏览器
        SSLClient httpclient = new SSLClient();

        //RequestConfig config = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000).build();

        //2.创建get请求,设置请求头 相当于在浏览器输入URL
        HttpGet get = new HttpGet(url);
        if(!headers.isEmpty()) {
            get.setHeader("Cookie", headers.get("Cookie"));
            get.setHeader("User-Agent", headers.get("User-Agent"));
        }

        //3.执行get请求,相当于输入网址后按回车键
        CloseableHttpResponse response=null;
        String html=null;
        try {
            response = httpclient.execute(get);
            //4.判断响应状态，进行相应处理
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                html = EntityUtils.toString(entity,"utf-8");
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

    public abstract List<T> getContext(String htm);

}
