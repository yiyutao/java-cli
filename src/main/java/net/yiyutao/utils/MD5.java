package net.yiyutao.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 *
 * @author PCF
 * 2017-10-10 15:30
 */
public class MD5 {

    /**
     * 对字符串进行MD5加密
     *
     * @param strSrc
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String md5(String strSrc) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(strSrc.getBytes("UTF-8"));
        byte[] b = md.digest();

        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }

    public static String getVal(String plainText)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] b = md.digest();

        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    /**
     * 给字符串进行md5加密
     * @param salt  盐
     * @param str   待加密字符串
     * @return
     */
    public static String md5(String salt,String str){
        String algorithmName = "MD5";
        Object source = str;
        int hashInterations = 1024;
        Object result = new SimpleHash(algorithmName, source, ByteSource.Util.bytes(salt), hashInterations);
        return result.toString();
    }
}
