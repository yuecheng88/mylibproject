package com.xiao.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MD5Util {


	private static final String MD5 = "MD5";

	public static String encrypt(byte[] value) {
		if (value == null)
			return "";

		MessageDigest md = null;
		String strDes = null;

		try {
			md = MessageDigest.getInstance(MD5);
			md.update(value);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		String tmp = null;
		for (int i = 0; i < byteArray.length; i++) {
			tmp = Integer.toHexString(byteArray[i] & 0xFF);
			if (tmp.length() == 1) {
				strBuf.append("0");
			}
			strBuf.append(tmp);
		}
		return strBuf.toString();
	}

	/**
	 * 生成MD5签名
	 * @param map
	 * @param key
	 * @param charset
	 * @return
	 */
	public static String md5Sign(Map<String, String> map, String key, String charset) {
		if (map == null || key == null || key.length() == 0) {
			return null;
		}

		ArrayList<String> lst = new ArrayList<String>();
		String s = "";
		for (String k : map.keySet()) {
			lst.add(k);
		}

		Collections.sort(lst, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		for (int i = 0; i < lst.size(); i++) {
			String k = lst.get(i);
			String v = map.get(k);
			s += k + "=" + v + "&";
		}
		s += "key=" + key;
		//System.out.println("OriginalString=[" + s + "]");
		String sign = "";
		try {
			//System.out.println("before sign=" + s);
			sign = MD5Coding.encode2HexStr(s.getBytes(charset));// encrypt(s.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println("sign=" + sign);
	   
		return sign;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("ver", "1");
//		params.put("openId", "");
//		System.out.println(md5SignwithKey(params, "UTF-8"));
		String sign = "";
		String s="appid=wxb45b8e101afc2205&attach={\"openId\":\"ous_Vs_0TGtRyabAnk4YuXl00Yvk\",\"buyerNickName\":"+URLEncoder.encode("\"郭其梅++++\"","UTF8")+",\"buyerUin\":0,\"sellerUin\":0,\"ifb\":0}&bank_type=CFT&cash_fee=32800&fee_type=CNY&is_subscribe=N&mch_id=10058865&nonce_str=18&openid=ous_Vs_0TGtRyabAnk4YuXl00Yvk&out_trade_no=YJ3961431097141640&result_code=SUCCESS&return_code=SUCCESS&time_end=20150508225910&total_fee=32800&trade_type=JSAPI&transaction_id=1009050113201505080117901402&key=05d92155c865202617b96f7cfb0402ed";
		try {
			System.out.println("before sign=" + s);
			sign = MD5Coding.encode2HexStr(s.getBytes("UTF8"));// encrypt(s.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("sign=" + sign);
	}

	public static String md5Sign(Map<String, String> map, String keyName, String keyValue, String charset) {
		if (map == null || keyValue == null || keyValue.length() == 0) {
			return null;
		}

		ArrayList<String> lst = new ArrayList<String>();
		String s = "";
		for (String k : map.keySet()) {
			lst.add(k);
		}

		Collections.sort(lst, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		for (int i = 0; i < lst.size(); i++) {
			String k = lst.get(i);
			String v = map.get(k);
			s += k + "=" + v + "&";
		}
		s += keyName + "=" + keyValue;
		// logger.info("OriginalString=[" + s + "]");
		String sign = "";
		try {
			sign = MD5Coding.encode2HexStr(s.getBytes(charset));// encrypt(s.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// logger.info("sign=" + sign);
		return sign;
	}
}
