/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengsihan
 * @date 2019-10-11 15:04
 * @desc 方法去和运行时常量池
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
       String str2 = new StringBuilder().append("计算机").append("软件").toString();
        System.out.println(str2.intern() == str2);


    }
}
