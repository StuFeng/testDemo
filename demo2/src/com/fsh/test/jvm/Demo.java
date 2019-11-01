/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test.jvm;

/**
 * @author fengsihan
 * @date 2019-10-22 20:04
 * @desc TODO
 */
public class Demo {

    public static void main(String[] args) {
        //        System.out.println(SubClass.value);// 对于静态字段，只有直接定义这个字段的类才会被初始化
        //        SuperClass[] sca = new SuperClass[10]; // 数组不会加载类

        System.out.println(ConstClass.HELLO_WORLD); // 常量单独处理，和类初始化无关
    }
}
