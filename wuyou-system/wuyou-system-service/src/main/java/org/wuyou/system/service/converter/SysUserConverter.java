package org.wuyou.system.service.converter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.wuyou.system.api.dto.user.CreateUserCmd;
import org.wuyou.system.api.dto.user.UserQuery;
import org.wuyou.system.api.dto.user.UserVO;
import org.wuyou.system.dao.entity.SysUserDO;

/**
 * @author origami
 * @date 2023/10/19 14:32
 */
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SysUserConverter {

    SysUserDO createUserCmdToDO(CreateUserCmd createUserCmd);

    UserVO DO2VO(SysUserDO sysUserDO);

    SysUserDO query2DO(UserQuery userQuery);

}
