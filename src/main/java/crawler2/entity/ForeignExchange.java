package crawler2.entity;

import crawler2.service.Crawler;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lp
 * @date 2020/9/2 10:59
 */

@Data
@Entity
public class ForeignExchange {

    @Id
    String currency;

    String currentPrice;

    String upAndDown;

}
