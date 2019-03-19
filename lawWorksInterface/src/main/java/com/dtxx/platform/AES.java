package com.dtxx.platform;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.dtxx.platform.ServerEncodeTest.AESKey1;
import static com.dtxx.platform.ServerEncodeTest.AESKey2;


/**
 * Created by cash on 2015/9/24.
 */
public class AES {

	/**
	 * 使用AES-128-CBC模式， 加密字符串
	 * 
	 * @param sSrc
	 *            需加密的字符串
	 * @param sKey
	 *            密钥
	 * @param sIv
	 *            偏移量
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt(String sSrc, String sKey, String sIv)
			throws Exception {
		if (sKey == null) {
			throw new Exception("Key为空null");
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			throw new Exception("Key长度不是16位");
		}
		if (sIv == null) {
			throw new Exception("IV为空null");
		}
		// 判断Key是否为16位
		if (sIv.length() != 16) {
			throw new Exception("IV长度不是16位");
		}
		sSrc = URLEncoder.encode(sSrc, "UTF-8");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());
		String res = Base64Util.encode(encrypted);
		return res;
	}

	/**
	 * 使用AES-128-CBC模式， 解密字符串
	 * 
	 * @param sSrc
	 *            需解密的字符串
	 * @param sKey
	 *            密钥
	 * @param sIv
	 *            偏移量
	 * @return
	 * @throws Exception
	 */
	public static String Decrypt(String sSrc, String sKey, String sIv)
			throws Exception {
		try {
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
			byte[] raw = sKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//			BASE64Decoder decoder = new BASE64Decoder();
//			byte[] encrypted1 = decoder.decodeBuffer(sSrc);
			byte[] encrypted1=Base64Util.decodeBuffer(sSrc);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original,"UTF-8");
				// if(originalString != null ){
				// originalString = originalString.replaceAll("%25","%");
				// }
				originalString = URLDecoder.decode(originalString, "UTF-8");  
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
}
