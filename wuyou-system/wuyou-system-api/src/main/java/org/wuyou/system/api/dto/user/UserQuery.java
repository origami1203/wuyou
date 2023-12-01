package org.wuyou.system.api.dto.user;

import lombok.Data;

/**
 * @author origami
 * @date 2023/10/20 9:24
 */
@Data
public class UserQuery {
    private String username;
    private String password;
    private String nickname;
    private Integer sex;
    private String logo;
}
