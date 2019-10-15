/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.aop.strategy;

import com.example.demo1.aop.annotation.Router;

/**
 * 取模策略
 *
 * @Auther: magenming
 * @Date: 2018/8/21 18:37
 */
public class ModStrategy {

    /**
     * 计算表名
     *
     * @param router
     * @param fieldValue
     *
     * @return
     */
    public static String calculate(Router router, String fieldValue) {
        String tableName = router.tableName();
        return tableName + "_" + mod(Long.parseLong(fieldValue), router.num());
    }

    private static long mod(long a, long b) {
        return a % b;
    }
}
