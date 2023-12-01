package org.wuyou.system.web;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wuyou.core.R;
import org.wuyou.system.api.SysUserService;
import org.wuyou.system.api.dto.user.CreateUserCmd;
import org.wuyou.system.api.dto.user.UserVO;
import org.wuyou.system.manager.mq.DefaultProducer;

/**
 * @author origami
 * @date 2023/10/17 11:20
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private DefaultProducer producer;

    @GetMapping("/{id}")
    public R<UserVO> getById(@PathVariable("id") Long id) {
        return R.ok(sysUserService.getById(id));
    }

    @PostMapping("")
    public R<UserVO> create(@RequestBody CreateUserCmd createUser) {
        return R.ok(sysUserService.createUser(createUser));
    }
}
