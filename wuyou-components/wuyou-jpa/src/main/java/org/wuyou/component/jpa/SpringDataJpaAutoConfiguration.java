package org.wuyou.component.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.wuyou.component.jpa.config.UsernameAuditorAware;

/**
 * @author origami
 * @date 2023/10/14 16:54
 */
@EnableJpaAuditing
@AutoConfigureBefore(JpaRepositoriesAutoConfiguration.class)
@RequiredArgsConstructor
public class SpringDataJpaAutoConfiguration {

    @PersistenceContext
    private final EntityManager entityManager;

    @Bean
    public AuditorAware<String> usernameAuditorAware() {
        System.out.println("lalalal");
        return new UsernameAuditorAware();
    }

    @Bean
    public JPAQueryFactory jpaQuery() {
        return new JPAQueryFactory(entityManager);
    }
}
