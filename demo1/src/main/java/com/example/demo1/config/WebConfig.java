package com.example.demo1.config;

/**
 * @author fengsihan
 * @date 2019-04-29 10:19
 * @desc TODO
 */
/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 全局配置，包含filter
 *
 * @author magenm
 * @date 2018/8/2 13:27
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截器已生效");
    }
}
