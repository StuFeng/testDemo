/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author fengsihan
 * @date 2019-10-24 15:07
 * @desc TODO
 */
public class FieldResolution {
    interface Interfece0 {
        int A = 0;
    }

    interface Interface1 extends Interfece0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        //        public static int A =4;
    }

    static class Sub1 implements Interfece0 {
        //        public static int A =4;
    }

    public static void main(String[] args) {
        System.out.println(Sub1.A);
        ClassLoader.getSystemClassLoader();
    }


}
