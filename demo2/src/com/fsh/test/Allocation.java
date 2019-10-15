/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test;

/**
 * @author fengsihan
 * @date 2019-10-14 17:10
 * @desc 测试 GC 日志
 */
public class Allocation {

    private static final int _1MB = 1024 * 1024;

    /**
     *
     * -verbose:gc  打印使用率
     * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testAllocation();
    }

}
