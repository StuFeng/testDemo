/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test.jvm;

/**
 * @author fengsihan
 * @date 2019-10-22 20:09
 * @desc TODO
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init");
    }
    public static final String HELLO_WORLD = "hello world";


}
