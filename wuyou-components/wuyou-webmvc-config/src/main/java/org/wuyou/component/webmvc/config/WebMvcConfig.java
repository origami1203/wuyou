package org.wuyou.component.webmvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wuyou.core.util.JsonUtils;

import java.util.List;

/**
 * @author origami
 * @date 2023/10/17 15:55
 */
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.debug("自定义MappingJackson2HttpMessageConverter已配置");
        // 使用自定义ObjectMapper
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        converters.add(0, new MappingJackson2HttpMessageConverter(mapper));
    }

}
