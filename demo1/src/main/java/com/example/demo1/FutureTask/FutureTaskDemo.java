/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.FutureTask;

import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author fengsihan
 * @date 2019-09-06 14:15
 * @desc TODO
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        long stime = System.currentTimeMillis();
        FutureTask<String> task = new FutureTask<>(() -> {
            Thread.sleep(2000);
            return "aa";
        });

        new Thread(task).start();

        try {
            System.out.println(task.get(3000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long etime = System.currentTimeMillis();
        System.out.println(etime-stime);
    }
}
