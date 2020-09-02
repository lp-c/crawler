package crawler2.dao;

import crawler2.entity.HouseInfo;
import org.springframework.data.repository.CrudRepository;

public interface HouseInfoReposity extends CrudRepository<HouseInfo,String> {
}
