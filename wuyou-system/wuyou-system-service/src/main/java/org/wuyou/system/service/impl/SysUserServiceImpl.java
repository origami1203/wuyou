package org.wuyou.system.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.wuyou.system.api.SysUserService;
import org.wuyou.system.api.dto.user.CreateUserCmd;
import org.wuyou.system.api.dto.user.UserVO;
import org.wuyou.system.dao.SysUserRepository;
import org.wuyou.system.dao.entity.SysUserDO;
import org.wuyou.system.service.converter.SysUserConverter;

import java.util.Optional;

/**
 * @author origami
 * @date 2023/10/19 10:11
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserConverter userConverter;
    @Resource
    private SysUserRepository userRepository;

    @Override
    public UserVO createUser(CreateUserCmd createUser) {
        SysUserDO sysUserDO = userConverter.createUserCmdToDO(createUser);
        userRepository.save(sysUserDO);
        return userConverter.DO2VO(sysUserDO);
    }

    @Override
    public UserVO getById(Long id) {
        Optional<SysUserDO> user = userRepository.findById(id);
        return user.map(sysUserDO -> userConverter.DO2VO(sysUserDO)).orElse(null);
    }

    // @Override
    // public Page<UserVO> getPage(PageQuery<UserQuery> query) {
    //     Page<SysUserDO> page = userRepository.getPage(query.mapToPageQuery(userConverter::query2DO));
    //     return page.map(userConverter::DO2VO);
    // }
}
