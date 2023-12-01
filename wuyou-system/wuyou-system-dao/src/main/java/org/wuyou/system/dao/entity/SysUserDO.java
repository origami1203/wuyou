package org.wuyou.system.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wuyou.component.jpa.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author origami
 * @date 2023/10/17 10:53
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "sys_user")
public class SysUserDO extends BaseEntity implements Serializable {

    private String username;

    private String nickname;

    private Integer sex;

    private String password;

    private String avatar;

    private LocalDate birthday;

}
