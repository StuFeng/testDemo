/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.config;

import lombok.Data;

/**
 * @author fengsihan
 * @date 2019-08-06 15:51
 * @desc TODO
 */
@Data
public class FshInfo {
    private String name;

    public void init() {
        System.out.println(name);
    }

}
