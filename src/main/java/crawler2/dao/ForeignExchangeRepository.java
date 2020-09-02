package crawler2.dao;

import crawler2.entity.ForeignExchange;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author lp
 * @date 2020/9/2 11:02
 */
public interface ForeignExchangeRepository extends CrudRepository<ForeignExchange, String> {

}

