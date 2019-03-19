package com.dtxx.platform;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by cash on 2015/9/24.
 */
public class RandomString {

    /**
     * 根据参数，生成固定长度的字符串
     * @param length
     * @return
     */
    private static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom random = null;
        StringBuffer sb = new StringBuffer();
        try {
			random = SecureRandom.getInstance("SHA1PRNG");
	        for (int i = 0; i < length; i++) {
	            int number = random.nextInt(base.length());
	            sb.append(base.charAt(number));
	        }
        }catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sb.toString();
    }

    /**
     * 生成16位固定长度的字符串
     * @return
     */
    public static String getRandomString16() {
        return getRandomString(16);
    }
}
