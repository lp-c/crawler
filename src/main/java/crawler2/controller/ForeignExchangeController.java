package crawler2.controller;

import crawler2.service.ForeignExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author lp
 * @date 2020/9/2 11:28
 * 现在的问题 获取不了动态内容
 */

@RestController
public class ForeignExchangeController {

    @Autowired
    ForeignExchangeService foreignExchangeService;

    @PostMapping("gethtml")
    public String getHtml() throws Exception {
       return foreignExchangeService.download("https://finance.sina.com.cn/forex/",new HashMap<>());
    }

}
