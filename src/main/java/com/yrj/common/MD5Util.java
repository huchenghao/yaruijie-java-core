package com.yrj.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static String salt = "liliyuanshangcao";
	/**
	 * 
	 * <p>Title: getMD5String</p>  
	 * <p>Description: 获取字符串的MD5值</p>  
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str) {
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return HexUtil.bytes2Hex(bytes);
    }
	/**
	 * 
	 * <p>Title: getMD5UpperString</p>  
	 * <p>Description: 获得字符串的md5大写值</p>  
	 * @param str
	 * @return
	 */
	public static String getMD5UpperString(String str) {
        return getMD5String(str).toUpperCase();
    }
	
	/**
	 * 
	 * <p>Title: getFileMD5String</p>  
	 * <p>Description: 获得文件的md5值</p>  
	 * @param file
	 * @return
	 */
	public static String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            ret = HexUtil.bytes2Hex(md5.digest());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
	/**
	 * 
	 * <p>Title: getFileMD5UpperString</p>  
	 * <p>Description:获得文件md5值大写字符串</p>  
	 * @param file
	 * @return
	 */
    public static String getFileMD5UpperString(File file) {
        return getFileMD5String(file).toUpperCase();
    }
    /**
     * 
     * <p>Title: checkMD5</p>  
     * <p>Description: 校验字符串的md5值</p>  
     * @param str
     * @param md5
     * @return
     */
    public static boolean checkMD5(String str, String md5) {
        return getMD5String(str).equalsIgnoreCase(md5);
    }
    /**
     * 
     * <p>Title: getMD5AndSalt</p>  
     * <p>Description: 获得加盐md5，算法过程是原字符串md5后连接加盐字符串后再进行md5</p>  
     * @param str
     * @param salt
     * @return
     */
    public static String getMD5AndSalt(String str, String salt) {
        return getMD5String(getMD5String(str).concat(salt));
    }
    /**
     * 
     * <p>Title: getMD5AndSalt</p>  
     * <p>Description: </p>  
     * @param str
     * @return
     */
    public static String getMD5AndSalt(String str) {
        return getMD5String(getMD5String(str).concat(salt));
    }
	
}
