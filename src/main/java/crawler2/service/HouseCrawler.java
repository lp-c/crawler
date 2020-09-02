package crawler2.service;

import crawler2.dao.HouseInfoRepository;
import crawler2.entity.HouseInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HouseCrawler extends Crawler<HouseInfo>{

    private HouseInfoRepository houseInfoRepository;

    @Autowired
    public HouseCrawler(HouseInfoRepository houseInfoRepository){
        this.houseInfoRepository = houseInfoRepository;
    }



    @Override
    public ArrayList<HouseInfo> getContext(String html){

        Document document = Jsoup.parse(html);
        String[] res={};
        ArrayList<HouseInfo> houseInfos = new ArrayList<>();
        Elements elements = document.select(".build_des");

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
            houseInfoRepository.save(houseInfo1);
        }
        return houseInfos;
    }

}
