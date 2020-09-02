package crawler2.service;

import crawler2.dao.PictureDao;
import crawler2.entity.Picture;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoftCrawler extends Crawler<Picture>{

    private PictureDao pictureDao;
    public LoftCrawler(PictureDao pictureDao){
        this.pictureDao=pictureDao;
    }

    @Override
    public ArrayList<Picture> getContext(String html)  {
        ArrayList<Picture> pictures = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("pattern");
        int i=1;
        for (Element element:elements) {
            String url1 = element.absUrl("src");
            Picture picture = new Picture();
            picture.setId(i);
            picture.setUrl(url1);
            pictures.add(picture);
            System.out.print(picture);
            i++;
            pictureDao.save(picture);
        }
        return pictures;
    }

}
