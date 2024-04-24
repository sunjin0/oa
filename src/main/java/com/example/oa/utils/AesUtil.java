package com.example.oa.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @description: AES加密工具类
 * @create:
 **/
public class AesUtil {
    // 秘钥 16
    private static final String sKey = "1111111111111111";
    // 秘钥 24
    //private static final String SECRET_KEY = "111111111111111122222222";
    // 秘钥 32
    //private static final String SECRET_KEY = "11111111111111112222222233333333";


    /**
     * 加密
     *
     * @param sSrc 需要加密的字符串
     * @return
     * @throws Exception
     */
    public static String Encrypt(String sSrc) throws Exception {

        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * 解密
     *
     * @param sSrc 需要解密的字符串
     * @return
     * @throws Exception
     */
    public static String Decrypt(String sSrc) throws Exception {

        // 判断Key是否正确
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密

        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, StandardCharsets.UTF_8);


    }

}
