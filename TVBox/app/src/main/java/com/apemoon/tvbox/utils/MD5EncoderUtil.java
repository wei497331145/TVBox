package com.apemoon.tvbox.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author water
 * @Date 2017-11-27
 * @des  MD5加密工具类
 */

public class MD5EncoderUtil {
    public static String encodeByMd5(String password) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(password.getBytes("UTF-8"));
            String hexString = byteArrayToHexString(byteArray);
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (byte b : byteArray) {
            String hexString = byteToHexString(b);
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static String byteToHexString(byte b) {// -31
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hex[d1] + hex[d2];
    }

    private static String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "x", "t", "u", "v", "w", "s", "y", "z"};

    public static void main(String[] args) throws Exception {
        String passwordMD5 = MD5EncoderUtil
                .encodeByMd5("merchid=901085100190001&service=app.unified.micropay.modifyshortname&shortName=测试&key=1AC05CE016B1C986D5EAE4EE6D120010");
        System.out.println(passwordMD5);
    }


}