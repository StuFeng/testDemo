/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test.weical;

import java.util.List;

/**
 * @author fengsihan
 * @date 2019-11-01 14:54
 * @desc 位运算
 */
public class TestCal {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);  // 超过128的数值不能自动拆箱
        System.out.println(c == (a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(a+b));
    }

}
