package org.wuyou.component.jpa.config;

import org.springframework.data.domain.AuditorAware;
import org.wuyou.core.util.UserContextHolder;

import java.util.Optional;

/**
 * @author origami
 * @date 2023/10/14 15:51
 */
public class UsernameAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(UserContextHolder.getUsername());
    }
}
