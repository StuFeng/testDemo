/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码图片流
 *
 * @author zhu
 */

public class QRCode {
    public static void main(String[] args) {
        //设置二维码宽度
        int width = 300;
        //设置二维码高度
        int height = 300;
        //设置图片格式
        String format = "png";
        //设置二维码的信息内容，就是扫了二维码后出现的东西，这里是一个链接
        String content = "baiduboxapp://swanEVmzlvTGXQQzIGSak2IGTQ6B6b144nBy/pages/zs/info/info?_baiduboxapp=%7B"
                + "%22from%22%3A%221180000000000000%22%7D&callback=_bdbox_js_275&id=889973&upgrade=0";
        Long stime = System.currentTimeMillis();
        createQRcode(width, height, format, content);
        Long etime = System.currentTimeMillis();
        System.out.println(etime - stime);
    }

    private static void createQRcode(int width, int height, String format, String content) {
        // 设置下二维码的参数
        Map hint = new HashMap();
        // 设置二维码的编码格式
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 设置二维码的纠错等级,等级越高，可存储的数据量就越少
        // L,M,Q,H这四个值等级依次有低到高
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置二维码的边距，也就是外面的空白边框大小
        hint.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hint);
            Path a = new File("/Users/baidu/Downloads/bb.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, a);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}

