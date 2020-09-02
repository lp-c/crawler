package crawler2.service;

import crawler2.entity.ForeignExchange;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lp
 * @date 2020/9/2 11:07
 * @des 爬取新浪财经的外汇信息
 */

@Service
public class ForeignExchangeService extends Crawler<ForeignExchange>{

    @Override
    public List<ForeignExchange> getContext(String htm) {
        return null;
    }
}
