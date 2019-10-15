/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.voltile;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fengsihan
 * @date 2019-09-29 20:46
 * @desc TODO
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        int b = race;
        while (true) {
            race++;
            if (b + 1 == race) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        condition.signal();
    }
}
