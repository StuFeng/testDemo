/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.jiami;

/**
 * @author fengsihan
 * @date 2019-07-03 11:28
 * @desc TODO
 */

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class Security {

    public static String encrypt(String input, String key) {

        byte[] crypted = null;

        try {
            byte[] bytesOfMessage = key.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(bytesOfMessage);
            SecretKeySpec skey = new SecretKeySpec(b, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, skey);

            crypted = cipher.doFinal(input.getBytes());

        } catch (Exception e) {

            System.out.println(e.toString());

        }

        return new String(Base64.encodeBase64(crypted));

    }

    public static String decrypt(String input, String key) {

        byte[] output = null;

        try {
            byte[] bytesOfMessage = key.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(bytesOfMessage);
            SecretKeySpec skey = new SecretKeySpec(b, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, skey);

            byte[] bytes = Base64.decodeBase64(input);
//            String aa = new String(bytes);
//            System.out.println(bytes.length);
//            output = cipher.doFinal(aa.substring(0, aa.length()/16 * 16).getBytes());

        } catch (Exception e) {

            System.out.println(e.toString());

        }

        return new String(output);

    }


    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        //生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) {

        String key = "5de18a37bdebbedf73de8193deab7285bccf19dd";

//        String data = "example";

        String data = "1u4+siUB2Q3yy+B3qzr8xc1YD2zPczajRU70AyP4LKkDfZaIxSlq/SFccEzeVR/tuWkqVoRJ0F0fShDU6/eMChEYqQcXznqWRwBOc+NisW+m/EBAHZg5UHAfHhKi3crWdy1JcguvnAyj5qxT5Lt6ZlYaz51quCyTQjtB0TF7j3xi7JePF3iqwBaAGowpbnQXCrkG1KDhBltTJnYF6gTwkzS1G+mOoKIKGIgAH6BaMtKgp63S2GBzOIvbLIKyLv4Nl+Y+1UnHClSNebkzsZl3VoJ3Hz5PSdEK4boHIN8yG0oqBWUORe9HtA7U2gKtEX7VgxUabCuSOkpR2lNwfG3AzftA8Tuc3I12q58fVrHvTvkSr2BMC8+1RuBSbRWjkVI5kIHOa5rXpDcRld2DXH6NFFJs2fn1giwDYDMGWb22X6k3ylU1DmMcMFYNvI15eo+MUP2kWlEkR4VI/8TLcgNDBPFB4KqWOEMTTEXmSaNhhZ/Jz8fiviTAgnWLCvO7wobmt4Fs9MRB/6B1wmXnvTKKnc5Mjua40BaYWnpqt2iDOafHu1tMV4jRprUFi7hQfAm10X//C/yL5tcNphBYUWKIbRZbSLrXrTTDVugXJMWLcdistYF0qe75yCHP2Pgu7Id1xJbwxIhaEKoqtJ/AKEGy+fqjBiizmdNe";
        System.out.println(data.getBytes().length);
        try {
            decryptByPrivateKey(data.getBytes(), key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        System.out.println(Security.encrypt(data, key));

//        System.out.println(Security.decrypt(Security.encrypt(data, key), key));
//        System.out.println(Security.decrypt(data, key));

    }

}
