package crawler2.service;

import crawler2.dao.HouseInfoReposity;
import crawler2.entity.HouseInfo;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MutilThreadHouseCrawler extends Crawler{

    private HouseInfoReposity houseInfoReposity;
    //特殊之处 这是一个接口 只能通过注入的方式使用 不能new一个实例来操作

    @Autowired
    public MutilThreadHouseCrawler(HouseInfoReposity houseInfoReposity){
        this.houseInfoReposity=houseInfoReposity;
    }
    /*这种方式会把Integer当成bean
    public MutilThreadHouseCrawler(Integer page,HouseInfoReposity houseInfoReposity){
        this.page=page;
        this.houseInfoReposity=houseInfoReposity;
    }*/

    @SneakyThrows
    //@SneakyThrows可以用来偷偷抛出已检查的异常而不在方法的throws子句中实际声明这一点
    @Async("test")
    public void run(int page) {
        //System.out.println("i'm coming");
        HashMap<String, String> headers = new HashMap<>();
        String cookie = "Hm_lvt_bbb8b9db5fbc7576fd868d7931c80ee1=1596530076; gr_user_id=e64485e8-5804-47e7-ab33-2e0821f89b64; grwng_uid=ac8b7aa6-0982-4103-ac6b-cb00dd2afe70; UM_distinctid=173b89bdf9770a-014dc4a4ad1e12-70657361-e1000-173b89bdf985ec; BSFIT_EXPIRATION=1596681170502; BSFIT_DEVICEID=CGRxUWwY1OJzmPGqcrpk_vqDuiGdJgoM1HUlr3t2HP3-kmzBykhU1-FwvRxlM3sEVcFakedtmR_rMWCAYQz3ZVLEmJiXkDbi7NEKdNfr6k5-ZAMKYPBmY-fXicpgIiZ_Q3WKY6RSVY4itpsH8zC3nmtxMnBhMm-A; br_access_code=3196mmtiUAwAAAAAfLMqXwAAAAD17Lm4; JSESSIONID=3121DCB70A53172B5033C3098A06F32A; BSFIT_+0rxn=; Hm_lpvt_bbb8b9db5fbc7576fd868d7931c80ee1=1596633632; CNZZDATA1253675216=1354437250-1596529531-%7C1596632286; BR_VAL_VID=3; BR_VAL_PASSCODE=B9U6pDz_vbIVmCrgGfRabhf2gguPKg5QHGDD9hokaOj27PGWogEsgLhd5afIYBjTKOsXuNKfNIbeLQLSVHEZZ-nfMEi6FXfp4CHeqD20S9p_6cTldY6RqeySlKaHgwAigmNEU2-AENvTD2SNq1fnZ587W17Fy-lH";
        String user_agent = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Mobile Safari/537.36";
        headers.put("Cookie", cookie);
        headers.put("User-Agent", user_agent);
        String url = "http://www.tmsf.com/newhouse/property_searchall.htm?searchkeyword=&keyword=&sid=&districtid=&areaid=&dealprice=&propertystate=&propertytype=&ordertype=&priceorder=&openorder=&view720data=&page=" + page + "&bbs=&avanumorder=&comnumorder=";
        String html = download(url, headers);
        //System.out.println(html);
        //System.out.println("i'm out");
        getContext(html);
    }

    public ArrayList<HouseInfo> getContext(String html) {
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
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        //System.out.println(houseInfos);
        //System.out.println("next i will store the info");
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        for (HouseInfo houseInfo1 :houseInfos) {
            houseInfoReposity.save(houseInfo1);
        }
        return houseInfos;
    }

    @Override
    public <T> T getContext(String htm, String pattern) {
        return null;
    }
}
