package com.tyilack.assist.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * VO DTO DO对象转换器
 * @author tyilack
 */
@Configuration
public class DozerBeanMapperConfig {

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        return beanMapper;
    }
}
