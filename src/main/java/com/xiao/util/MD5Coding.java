package com.xiao.util;

import java.security.MessageDigest;

/**
 * md5加密
 * 
 *
 */
public class MD5Coding
{

  public static byte[] encode(byte[] bytes)
  {
    MessageDigest digest;
    try
    {
      digest = MessageDigest.getInstance("MD5");
      digest.update(bytes);
      byte[] digesta = digest.digest();
      return digesta;
    }
    catch (Exception e) {
    }
    return null;
  }

  public static String encode2HexStr(byte[] bytes)
  {
    return HexUtil.bytes2HexStr(encode(bytes));
  }

}