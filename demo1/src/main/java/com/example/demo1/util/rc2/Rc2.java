/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.util.rc2;

import javax.crypto.Cipher;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author fengsihan
 * @date 2019-08-05 19:49
 * @desc TODO
 */
public class Rc2 {
    public static String decrypt(byte[] key, String encrypted) throws Exception {
        RC2ParameterSpec ivSpec = new RC2ParameterSpec(1024);
        Cipher cipher = Cipher.getInstance("RC2/ECB/NoPadding");
        SecretKeySpec rc2 = new SecretKeySpec(key, "RC2");
        cipher.init(Cipher.DECRYPT_MODE, rc2, ivSpec);
        byte[] decrypted = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes("UTF-8")));

        String s = Base64.encodeBase64String(decrypted);
        byte[] bytes = Base64.decodeBase64(s);
        System.out.println(s);
        return new String(decrypted,"UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String key = "edf73de8ebb93dea1dd285bccf195de18a37bdb7";
        String str =
                "1u4+siUB2Q3yy+B3qzr8xdqF9Of9VK9TWFBbmk0HXR4DfZaIxSlq/QHr9p7P0KEZw+f3D2nY3ELznokeUldBOiJ"
                        + "+26ed8Kor0I8Qbj7D+rnPBTfflx/WfK9C9V7r3nMhpsmt4ka8rJu9drtydc470C9WncN+2KhpAt+FILCvmYgR"
                        + "+ABqss5EJfFB4KqWOEMTTEXmSaNhhZ/Jz8fiviTAgnWLCvO7wobmt4Fs9MRB/6DVJ0ni"
                        + "+NK2ZSylQcfYUTMTC3az2BTk2nVmSuW0wPOapx/3Nv59j27C3Oc8Re5SSO7GXyYT7xcJ0USmN4LoEBF2X7obeW8"
                        + "/JUZUHJeHBaCbI94202FqKcifFXA+JQTq2B9AF2YcM7GSBQvQ4dPcl3DrbwEFjS/wHbcEsYpsu3QAeS0P"
                        + "+Oa5EB4dh2dLsc8xDNIOuaQpfxMs9/dF9st6EwtP1Hgr0IX51LyXYtyUSWJrt5p63qvy"
                        + "/viq5orYedLduwB2Lwho4OJYdCNqZYh4E6VAnwrCkefaXqcistFqF9ru8ZtYoiq3XiHTPVaA1iNW"
                        + "+QJxghVwqMAPLwsOQLN4dEWHJlXzs+H96zcv"
                        + "+yXdZZ0fdUjLmDz4KrRCg2yw9QyXdCi9fin23kQNtKzok7Yfp7mzlNRzJU8w3s5+bZJ57L/MqkpGSr3E1PzJ";

        System.out.println(decrypt(key.getBytes(), str));


    }
}
