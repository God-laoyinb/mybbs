package com.bbs.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
  *  字符串转MD5工具类
 * @author 啊哈
 *
 */
public class MD5Util {
	
	/**
	  *  字符串转MD5
	 * @param 字符串
	 * @return 经过MD5编码的字符串
	 */
	public static String toMd5(String plainText) {
   
        byte[] secretBytes = null;
        
        try {
        	MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
