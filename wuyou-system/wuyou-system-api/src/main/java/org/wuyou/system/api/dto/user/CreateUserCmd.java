package org.wuyou.system.api.dto.user;

import lombok.Data;

/**
 * @author origami
 * @date 2023/10/19 14:22
 */
@Data
public class CreateUserCmd {
    private String username;
    private String password;
    private String nickname;
    private Integer sex;
    private String logo;
}
