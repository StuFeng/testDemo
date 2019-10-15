/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1;

/**
 * @author fengsihan
 * @date 2019-06-18 15:43
 * @desc TODO
 */
public class StringLength {

    public static void main(String[] args) {
//        String aa = "{\"amdContent\":0,\"amdTool\":0,\"amdGame\":0,\"amdService\":0,\"amdToolLevel2\":0,"
//                + "\"amdGameLevel2\":0,\"amdContentGraphic\":0,\"amdContentVideo\":0,\"amdContentAnswer\":0,"
//                + "\"amdContentLive\":0,\"amdContentAudio\":0,\"amdContentLocGraphic\":0,\"amdContentLocAudio\":0}";
//
//        System.out.println(aa.length());

        aa();
    }

    public static String  aa(){
        String bb = "";
        try {
            bb = "11";
            throw new IndexOutOfBoundsException("数据越界");
        }catch (Exception e){
            bb = "22";
            throw new IndexOutOfBoundsException("数据越界");
        }finally {
            System.out.println("执行完成" + bb);
        }


    }
}
