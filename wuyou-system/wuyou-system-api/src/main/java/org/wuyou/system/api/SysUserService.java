package org.wuyou.system.api;

import org.wuyou.system.api.dto.user.CreateUserCmd;
import org.wuyou.system.api.dto.user.UserVO;

/**
 * @author origami
 * @date 2023/10/19 9:34
 */
public interface SysUserService {

    /**
     * Creates a new user.
     *
     * @param createUser the command containing the user details
     * @return the created user
     */
    UserVO createUser(CreateUserCmd createUser);

    UserVO getById(Long id);

    // Page<UserVO> getPage(PageQuery<UserQuery> query);

}
