package org.wuyou.system.dao;

import org.springframework.stereotype.Repository;
import org.wuyou.component.jpa.extension.BaseRepository;
import org.wuyou.system.dao.entity.SysUserDO;

/**
 * @author origami
 * @date 2023/10/17 11:19
 */
@Repository
public interface SysUserRepository extends BaseRepository<SysUserDO> {}
