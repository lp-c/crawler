package crawler2.service;

import crawler2.entity.Picture;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BaiduCrawler extends Crawler{
    @Override
    public ArrayList<Picture> getContext(String html, String pattern) {
        Document document = Jsoup.parse(html);
        System.out.print(html);
        return null;
    }
}
