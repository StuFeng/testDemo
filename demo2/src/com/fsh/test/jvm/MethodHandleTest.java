/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh.test.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author fengsihan
 * @date 2019-10-30 17:14
 * @desc TODO
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println("hello:MethodHandleTest");
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        //        Object obj =
        //                //                System.currentTimeMillis() % 2 == 0 ? System.out :
        //                new ClassA();
        //        getPrintlnMH(obj).invokeExact("icyfenix");
        //        getPrintlnMH(obj).invoke("icyfenix");
        new Son().thinking();
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
        /**
         * 第一个参数方法返回值，第二个参数方法类型
         */
        MethodType methodType = MethodType.methodType(void.class, String.class);
        /**
         * 指定类中查找符合给定的方法名称和方法类型，并且符合调用权限的方法句柄
         * bindTo 代表方法的接收者，即 this 指向的对象
         */
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType)
                .bindTo(receiver);
    }
}

class GrandFather {
    void thinking() {
        System.out.println("i am your grandfather");
    }
}

class Father extends GrandFather {
    void thinking() {
        System.out.println("i am your father");
    }
}

class Son extends Father {
    void thinking() {
        try {
            MethodType methodType = MethodType.methodType(void.class);
            MethodHandle methodHandle = MethodHandles.lookup()
                    .findSpecial(GrandFather.class, "thinking", methodType, getClass()).bindTo(this);
            methodHandle.invoke();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //使用MethodType构造出要调用方法的返回类型
        MethodType methodType = MethodType.methodType(void.class);
        try {
            //找到祖父类的构造函数
            MethodHandle inithandle = MethodHandles.lookup().findConstructor(GrandFather.class, methodType);
            //获取祖父类实例对象
            Object o = inithandle.invoke();
            //找到祖父类里被覆写的方法并把该方法绑定到祖父类实例上
            MethodHandle handle = MethodHandles.lookup()
                    .findVirtual(GrandFather.class, "thinking", methodType).bindTo(o);
            //调用祖父类里被父类覆写的方法
            handle.invoke();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
