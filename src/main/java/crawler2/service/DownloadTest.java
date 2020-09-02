package crawler2.service;

import crawler2.entity.HouseInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import static crawler2.utils.Download.download;

//用于能否爬取内容以及解析是否正确
public class DownloadTest {
    public static void main(String[] args) throws Exception {
        String url="http://www.tmsf.com/newhouse/property_searchall.htm?searchkeyword=%E8%AA%89%E5%BA%9C&keyword=%25u8A89%25u5E9C&sid=&districtid=&areaid=330181&dealprice=&propertystate=&propertytype=&ordertype=&priceorder=&openorder=&view720data=&page=1&bbs=&avanumorder=&comnumorder=";
        HashMap<String, String> headers = new HashMap<>();
        String cookie="BSFIT_EXPIRATION=1596600489847; BSFIT_DEVICEID=Bb8rYIA2apPFjgArtFUYV2Y7xi-5kgzC2Mlfq6DxB_tomecDGdBSFX1cJS6juv5TVwig9HFz2EEw6YEKZhqP-SixrAlI7W9YkQMDc1AlrdFD_gzYX3rIUjb6tpikXfx9ECc4Gj-71TgbxqQpLYEzrpO04VgiTfyD; Hm_lvt_bbb8b9db5fbc7576fd868d7931c80ee1=1596530076; gr_user_id=e64485e8-5804-47e7-ab33-2e0821f89b64; grwng_uid=ac8b7aa6-0982-4103-ac6b-cb00dd2afe70; UM_distinctid=173b89bdf9770a-014dc4a4ad1e12-70657361-e1000-173b89bdf985ec; br_access_code=3196mmtiUAwAAAAAc2opXwAAAACskyFr; BSFIT_rz9ym=; JSESSIONID=38E05E1DF0134DDBFA8CF418DF0EBE21; b61f24991053b634_gr_session_id=9d31c8ab-a901-45be-a588-df26c222d2f0; Hm_lpvt_bbb8b9db5fbc7576fd868d7931c80ee1=1596551310; CNZZDATA1253675216=1354437250-1596529531-%7C1596551136; b61f24991053b634_gr_session_id_9d31c8ab-a901-45be-a588-df26c222d2f0=true";
        String user_agent="Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Mobile Safari/537.36";
        headers.put("Cookie",cookie);
        headers.put("User-Agent",user_agent);
        String html = download(url,headers);
        //System.out.print(html);
        Document document = Jsoup.parse(html);
        System.out.println("@@@@@@@@@@@@@@@@@@");
        String[] res={};
        HouseInfo houseInfo = new HouseInfo();
        //分成两块来提取 howsell可以选出可售 总售 build_des 下的build_txt选出其他信息
        //分块提取带来的一个新问题 存储时怎么存储
        /*
        Elements elements1 = document.select(".howsell");
        for (Element element:elements1) {
            String text=element.text();
            System.out.println(text);
        }
        Elements elements2 = document.select(".build_des .build_txt");
        for (Element element:elements2) {
            String text=element.text();
            System.out.println(text);
        }*/
        //第一个返回的文本中多了一个“全景”
        Elements elements = document.select(".build_des");
        for(Element element:elements){
            System.out.println(element.text());
        }
        /*
        for (Element element:elements) {
            String text=element.text();
            System.out.println(text);
            res = text.split("\\s+");
            for (String r:res) {
                System.out.println(r);
            }
        }
        */
        /*
        for(int i=0;i<elements.size();i++){
            Element element=elements.get(i);
            String text=element.text();
            res = text.split("\\s+");
            if(i==0){//把第一个单独提出来处理
                houseInfo.setOnSale(res[1]);
                houseInfo.setAvailableForSale(res[2]);
                houseInfo.setName(res[3]);
                houseInfo.setStatus(res[4]);
                houseInfo.setType(res[6]);
                houseInfo.setSite(res[8]);
                houseInfo.setNews(res[10]);
            }
            else{
                houseInfo.setOnSale(res[0]);
                houseInfo.setAvailableForSale(res[1]);
                houseInfo.setName(res[2]);
                houseInfo.setStatus(res[3]);
                houseInfo.setType(res[5]);
                houseInfo.setSite(res[7]);
                houseInfo.setNews(res[9]);

            }
            System.out.println(houseInfo);
        }
         */
        //System.out.println(res);
    }
}
