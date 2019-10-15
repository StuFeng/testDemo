/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengsihan
 * @date 2019-08-06 15:50
 * @desc TODO
 */
@Configuration
public class BeanConfig {
    @Value("${fsh.name}")
    private String name;

    @Bean
    public FshInfo fshInfo() {
        FshInfo info = new FshInfo();
        info.setName(name);
        info.init();
        return info;
    }
}
