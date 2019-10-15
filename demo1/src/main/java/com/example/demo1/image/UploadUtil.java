/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.image;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author fengsihan
 * @date 2019-09-29 15:14
 * @desc TODO
 */
public class UploadUtil {

    /**
     * 根据文件流判断图片类型 只支持 jpg/jpeg/png/gif/bmp
     *
     * @param inputStream 文件
     *
     * @return 文件格式
     */
    public static FileTypeEnum getFileType(InputStream inputStream) throws IOException {

        String fileHeader = getFileHeader(inputStream);
        if (fileHeader == null) {
            return FileTypeEnum.unknown;
        }
        fileHeader = fileHeader.toUpperCase();
        if (fileHeader.contains("FFD8FF")) {
            return FileTypeEnum.jpg;
        } else if (fileHeader.contains("89504E47")) {
            return FileTypeEnum.png;
        } else if (fileHeader.contains("47494638")) {
            return FileTypeEnum.gif;
        } else if (fileHeader.contains("424D")) {
            return FileTypeEnum.bmp;
        } else if (fileHeader.contains("255044462D312E")) {
            return FileTypeEnum.pdf;
        } else if (fileHeader.contains("D0CF11E0")) {
            return FileTypeEnum.xls;
        }
        return FileTypeEnum.unknown;
    }

    private static String getFileHeader(InputStream inputStream) throws IOException {
        inputStream.mark(4);
        byte[] b = new byte[4];
        inputStream.read(b, 0, b.length);
        inputStream.reset();
        return bytesToHexString(b);
    }

    /**
     * byte数组转换成16进制字符串
     *
     * @param src byte数组
     *
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
