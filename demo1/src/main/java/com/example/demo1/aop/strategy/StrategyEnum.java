/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.aop.strategy;

/**
 * 分表策略
 *
 * @Auther: magenming
 * @Date: 2018/8/21 18:17
 */
public enum StrategyEnum {
    MODE(1, "取模"),
    RANGE(2, "区间");

    int type;
    String desc;

    StrategyEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }


    public String getDesc() {
        return desc;
    }

}
