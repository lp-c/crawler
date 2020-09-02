package crawler2.controller;

import crawler2.dao.HouseInfoReposity;
import crawler2.service.HouseCrawler;
import crawler2.service.MutilThreadHouseCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HouseInfoStore {

    private HouseCrawler houseCrawler;
    private MutilThreadHouseCrawler mutilThreadHouseCrawler;

    @Autowired
    public HouseInfoStore(HouseCrawler houseCrawler,MutilThreadHouseCrawler mutilThreadHouseCrawler){
        this.houseCrawler=houseCrawler;
        this.mutilThreadHouseCrawler=mutilThreadHouseCrawler;
    }

    @GetMapping("/houseinfo")
    public String storeInfo() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        String cookie = "BSFIT_EXPIRATION=1596600489847; BSFIT_DEVICEID=Bb8rYIA2apPFjgArtFUYV2Y7xi-5kgzC2Mlfq6DxB_tomecDGdBSFX1cJS6juv5TVwig9HFz2EEw6YEKZhqP-SixrAlI7W9YkQMDc1AlrdFD_gzYX3rIUjb6tpikXfx9ECc4Gj-71TgbxqQpLYEzrpO04VgiTfyD; Hm_lvt_bbb8b9db5fbc7576fd868d7931c80ee1=1596530076; gr_user_id=e64485e8-5804-47e7-ab33-2e0821f89b64; grwng_uid=ac8b7aa6-0982-4103-ac6b-cb00dd2afe70; UM_distinctid=173b89bdf9770a-014dc4a4ad1e12-70657361-e1000-173b89bdf985ec; br_access_code=3196mmtiUAwAAAAAc2opXwAAAACskyFr; BSFIT_rz9ym=; JSESSIONID=38E05E1DF0134DDBFA8CF418DF0EBE21; b61f24991053b634_gr_session_id=9d31c8ab-a901-45be-a588-df26c222d2f0; Hm_lpvt_bbb8b9db5fbc7576fd868d7931c80ee1=1596551310; CNZZDATA1253675216=1354437250-1596529531-%7C1596551136; b61f24991053b634_gr_session_id_9d31c8ab-a901-45be-a588-df26c222d2f0=true";
        String user_agent = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Mobile Safari/537.36";
        headers.put("Cookie", cookie);
        headers.put("User-Agent", user_agent);
        for(int i=1;i<2;i++) {
            String url = "http://www.tmsf.com/newhouse/property_searchall.htm?searchkeyword=&keyword=&sid=&districtid=&areaid=&dealprice=&propertystate=&propertytype=&ordertype=&priceorder=&openorder=&view720data=&page=" + i + "&bbs=&avanumorder=&comnumorder=";
            String html = houseCrawler.download(url, headers);
            houseCrawler.getContext(html);
        }
        return "抓取完毕";
    }

    @GetMapping("/info")
    public String info() throws InterruptedException {
        //问题是啥？
        //现在要跑多线程 需要把service提供的download和getContext方法都封装在实现了Runnable类的run方法中
        //和上面不同的地方在于 上面是注入service层的bean之后通过该对象调用service层提供的方法
        //而此处需要new 一个实现了Runnable接口的类
        for(int i=0;i<100;i++){
            //ExecutorService pool = Executors.newCachedThreadPool();
            System.out.println(Thread.currentThread().getName());
            mutilThreadHouseCrawler.run(i);
            //pool.execute(new MutilThreadHouseCrawler(i));
        }
        //Thread.sleep(10000);
        return "hello";
    }

    @GetMapping("/infotest")
    public String infoTest(){
        mutilThreadHouseCrawler.run(1);
        return "hello lp";
    }
}
