package crawler2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Picture {
    @Id
    Integer id;
    String url;
}
