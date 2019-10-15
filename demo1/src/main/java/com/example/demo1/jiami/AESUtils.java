/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.jiami;

/**
 * @author fengsihan
 * @date 2019-07-02 18:12
 * @desc TODO
 */

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class AESUtils {

    private final static String KEY = "5de18a37bdebbedf73de8193deab7285bccf19dd";

    /**
     * aes 加密
     *
     * @param data
     *
     * @return
     */
    public static String encrypt(String input) {
        byte[] crypted = null;
        try {
            byte[] bytesOfMessage = KEY.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(bytesOfMessage);
            SecretKeySpec skey = new SecretKeySpec(b, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(Base64.encodeBase64(crypted));
    }
    /**
     * aes 解密
     *
     * @param data 密文
     *
     * @return
     */
    public static String decryptData(String data) {
        try {
            byte[] bytesOfMessage = KEY.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(bytesOfMessage);
            SecretKeySpec skey = new SecretKeySpec(b, "AES");
            byte[] encrypted1 = Base64.decodeBase64(data);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);

            byte[] original = cipher.doFinal(encrypted1);

            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) {
        String data = "1u4+siUB2Q3yy+B3qzr8xc1YD2zPczajRU70AyP4LKkDfZaIxSlq/SFccEzeVR/tuWkqVoRJ0F0fShDU6/eMChEYqQcXznqWRwBOc+NisW+m/EBAHZg5UHAfHhKi3crWdy1JcguvnAyj5qxT5Lt6ZlYaz51quCyTQjtB0TF7j3xi7JePF3iqwBaAGowpbnQXCrkG1KDhBltTJnYF6gTwkzS1G+mOoKIKGIgAH6BaMtKgp63S2GBzOIvbLIKyLv4Nl+Y+1UnHClSNebkzsZl3VoJ3Hz5PSdEK4boHIN8yG0oqBWUORe9HtA7U2gKtEX7VgxUabCuSOkpR2lNwfG3AzftA8Tuc3I12q58fVrHvTvkSr2BMC8+1RuBSbRWjkVI5kIHOa5rXpDcRld2DXH6NFFJs2fn1giwDYDMGWb22X6k3ylU1DmMcMFYNvI15eo+MUP2kWlEkR4VI/8TLcgNDBPFB4KqWOEMTTEXmSaNhhZ/Jz8fiviTAgnWLCvO7wobmt4Fs9MRB/6B1wmXnvTKKnc5Mjua40BaYWnpqt2iDOafHu1tMV4jRprUFi7hQfAm10X//C/yL5tcNphBYUWKIbRZbSLrXrTTDVugXJMWLcdistYF0qe75yCHP2Pgu7Id1xJbwxIhaEKoqtJ/AKEGy+fqjBiizmdNe";

        System.out.println(KEY.getBytes().length);
        //
        //        String enStr = AESUtils.encrypt(data);
//        System.out.println("加密:" + enStr);

//        byte[] encryptedBytes = Base64.decodeBase64(data);

        String deStr = AESUtils.decryptData(data);
        System.out.println("解密:" + deStr);
    }

}
