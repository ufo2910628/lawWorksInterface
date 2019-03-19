package com.dtxx.platform;

import java.security.MessageDigest;
import java.util.Date;

import org.bouncycastle.util.encoders.Hex;

/**
 * Created by cash on 2015/9/24.
 */
public class SHA256 {
	/**
	 * 将字符串转换为sha256
	 * 
	 * @param inStr
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String inStr) throws Exception {
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(inStr.getBytes("UTF-8"));
		// System.out.println(res.length());
		  byte[] output = Hex.encode(hash);
		String res = Base64Util.encode(output);
		return res;
	}

	public static void main(String[] arg) {
//		String a = "abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复abc123你好范德萨发反反复复凤飞飞反复反复反复反复反复反复反复反复反复反复反复";
//		try {
//			long time1 = new Date().getTime();
//			System.out.println(ServerEncodeTest.encodeData(a));
//			System.out.println(new Date().getTime() - time1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
