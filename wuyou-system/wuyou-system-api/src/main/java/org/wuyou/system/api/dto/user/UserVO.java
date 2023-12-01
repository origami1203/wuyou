package org.wuyou.system.api.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author origami
 * @date 2023/10/19 16:49
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserVO {

    private String username;

    private String nickname;

    private String sex;

    private String avatar;

    private LocalDate birthday;

}
