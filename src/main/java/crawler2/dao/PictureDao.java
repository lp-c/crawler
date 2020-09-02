package crawler2.dao;

import crawler2.entity.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PictureDao extends CrudRepository<Picture, Integer> {
}
