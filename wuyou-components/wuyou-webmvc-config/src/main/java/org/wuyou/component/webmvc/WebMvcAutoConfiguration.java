package org.wuyou.component.webmvc;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.wuyou.component.webmvc.config.GlobalExceptionHandler;
import org.wuyou.component.webmvc.config.WebMvcConfig;

import java.util.Arrays;

/**
 * @author origami
 * @date 2023/10/17 14:33
 */
@Slf4j
@AutoConfigureBefore(ValidationAutoConfiguration.class)
@Import({GlobalExceptionHandler.class, WebMvcConfig.class})
public class WebMvcAutoConfiguration {

    /**
     * 过滤器配置跨域，比拦截器跨域的优先级高
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        log.debug("跨域已配置");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setMaxAge(1800L);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CorsFilter(urlBasedCorsConfigurationSource));
        filterRegistrationBean.setOrder(-100);
        return filterRegistrationBean;
    }

    /**
     * 设置validation failFast
     */
    @Bean
    public Validator validator(AutowireCapableBeanFactory springFactory) {
        log.debug("自定义bean validator已生效");
        return Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .constraintValidatorFactory(new SpringConstraintValidatorFactory(springFactory))
            .buildValidatorFactory()
            .getValidator();
    }

}
