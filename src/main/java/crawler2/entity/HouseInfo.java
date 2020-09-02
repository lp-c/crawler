package crawler2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class HouseInfo {
    @Id
    String name;
    String status;
    String onSale;
    String availableForSale;
    String site;
    String news;
    String type;//类型
    //Integer price;价格现在爬不下来
}
