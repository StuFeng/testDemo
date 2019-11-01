/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test.jvm;

/**
 * @author fengsihan
 * @date 2019-10-22 20:03
 * @desc TODO
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public static int value = 123;
}
