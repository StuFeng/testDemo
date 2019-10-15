/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengsihanF
 * @date 2019-10-09 14:23
 * @desc 测试堆转储快照
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
