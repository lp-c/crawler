package crawler2.controller;

import crawler2.entity.Pattern;
import crawler2.entity.Picture;
import crawler2.service.LoftCrawler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
//获取请求信息
public class GetRequest {

    private LoftCrawler crawler;

    public GetRequest(LoftCrawler crawler){
        this.crawler=crawler;
    }

    @GetMapping("/request")
    public String getRequest(Model model){
        Pattern pattern = new Pattern();
        model.addAttribute("pattern",pattern);
        return "request";
    }

    @PostMapping("/submit")
    public String getSubmit(@ModelAttribute Pattern pattern, Model model) throws Exception {
        //前端的数据到底是传给了patter还是model
        model.addAttribute("pattern",pattern);
        //HashMap<String, String> headers = new HashMap<>();
        //headers=null;
        ArrayList<Picture> context = crawler.getContext(crawler.download(pattern.getUrl(),new HashMap<>()));
        model.addAttribute("context",context);
        return "result";
    }
}
