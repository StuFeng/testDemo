/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo1.aop.strategy.StrategyEnum;

/**
 * 分表路由
 *
 * @author magenm
 * @date 2018/8/8 15:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Router {

    /**
     * 分表字段
     */
    String field();

    /**
     * 表前缀
     */
    String tableName();

    /**
     * 分表策略
     */
    StrategyEnum strategy() default StrategyEnum.MODE;

    /**
     * 因子
     */
    long num() default 8;

}
