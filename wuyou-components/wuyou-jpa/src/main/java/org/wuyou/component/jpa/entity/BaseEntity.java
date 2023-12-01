package org.wuyou.component.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.wuyou.component.jpa.config.SnowflakeIdGenerator;

import java.time.LocalDateTime;

/**
 * @author origami
 * @date 2023/10/14 16:27
 */
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", type = SnowflakeIdGenerator.class)
    @Column(columnDefinition = "bigint unsigned not null comment '主键'")
    protected Long id;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(columnDefinition = "datetime not null comment '创建日期'")
    protected LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @LastModifiedDate
    @Column(columnDefinition = "datetime not null comment '更新日期'")
    protected LocalDateTime updateTime;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(columnDefinition = "varchar(50) comment '创建人'")
    protected String createBy;

    /**
     * 最后更新人
     */
    @LastModifiedBy
    @Column(columnDefinition = "varchar(50) comment '最后更新人'")
    protected String updateBy;

    /**
     * 乐观锁
     */
    @Version
    @Column(columnDefinition = "bigint unsigned default 0 not null comment '版本号'")
    protected Long version;

    @Column(columnDefinition = "varchar(255) default '' comment '备注'")
    protected String remark;
}
