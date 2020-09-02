package crawler2.service;

import crawler2.dao.HouseInfoReposity;
import crawler2.entity.HouseInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

import static crawler2.utils.Download.download;

@Service
public class HouseCrawler extends Crawler{

    private HouseInfoReposity houseInfoReposity;

    @Autowired
    public HouseCrawler(HouseInfoReposity houseInfoReposity){
        this.houseInfoReposity=houseInfoReposity;
    }

    @Override
    public <T> T getContext(String htm, String pattern) {
        //解析html 并且入库
        return null;
    }

    public ArrayList<HouseInfo> getContext(String html){
        Document document = Jsoup.parse(html);
        String[] res={};
        ArrayList<HouseInfo> houseInfos = new ArrayList<>();
        Elements elements = document.select(".build_des");
        /*
        for(int i=0;i<elements.size();i++){
            HouseInfo houseInfo = new HouseInfo();
            //上面的语句
            //在循环体外，始终都是这一个对象，循环放入的都是最新的值
            //在循环体内，创建的是不同的对象，每次放入的对应这不同值的对象
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
            houseInfos.add(houseInfo);
        }
         */
        for (Element element:  elements) {
            String text=element.text();
            res = text.split("\\s+");
            HouseInfo houseInfo = new HouseInfo();
            if(res[0].equals("全景")){
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
            houseInfos.add(houseInfo);
        }
        for (HouseInfo houseInfo1 :houseInfos) {
            houseInfoReposity.save(houseInfo1);
        }
        return houseInfos;
    }
}
