package regular.model.com.entity.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * OBJECTLAR DB GA QO'SHILGANDA YOKI O'ZGARTIRILGANDA
 * AVTOMAT RAVISHDA O'SHA VAQTNI OLISHI UCHUN
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbsDateAudit implements Serializable {
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Objects.nonNull(this.createdAt) ?
                this.createdAt : new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Objects.nonNull(this.updatedAt) ? this.updatedAt : new Date();
    }

}
