package org.wuyou.component.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 带有逻辑删除的基类
 *
 * @author origami
 * @date 2023/10/14 16:47
 */
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseLogicalDeleteEntity extends BaseEntity {

    @Column(name = "is_deleted", columnDefinition = "bit(1) default '0' comment '逻辑删除标识,0为未删除,1为删除'")
    protected Boolean deleted;

}
