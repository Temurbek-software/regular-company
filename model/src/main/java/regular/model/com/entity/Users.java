package regular.model.com.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import regular.model.com.entity.template.AbsLongEntity;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "users")
@NoArgsConstructor
public class Users extends AbsLongEntity {

}
