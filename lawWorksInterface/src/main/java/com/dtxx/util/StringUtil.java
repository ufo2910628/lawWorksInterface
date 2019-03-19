package com.dtxx.util;

import java.util.UUID;

/**
 * <p>
 * String工具类.
 * </p>
 * 
 * @author Wang_Jie
 * @Date 2013-09-04
 * 
 */
public class StringUtil implements java.io.Serializable {

	private static final long serialVersionUID = 300583967054897189L;
	public static final String replaceStrKay = "-";
	public static final String replaceResultStrValue = "";
	public static final int INT_15 = 15;
    public static int serialNumber = 1000;
	/**
	 * <p>
	 * null
	 * </p>
	 */
	public static final String STR_LOWER_NULL = "null";
	/**
	 * <p>
	 * NULL
	 * </p>
	 */
	public static final String STR_UPPER_NULL = "NULL";

	/**
	 * <p>
	 * 判断String是空
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(String obj) {
		if (null == obj || "".equals(obj) || obj.length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * <p>
	 * 判断String非空
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(String obj) {
		return !StringUtil.isEmpty(obj);
	}

	/**
	 * <p>
	 * 生成32位字符串.
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static synchronized String makeUUID() throws Exception {

		StringBuffer result = new StringBuffer();
		UUID uuid = UUID.randomUUID();
		result.append(DateUtil.parseDateToStrDateIsNullReturnNow(null, DateUtil.FORMAT_TEMPLATE_STR_3));
		result.append(uuid.toString().replace(StringUtil.replaceStrKay,
				StringUtil.replaceResultStrValue).subSequence(0,
				StringUtil.INT_15));

		return result.toString();
	}


    public static synchronized String makeOutTradeNo() throws Exception {
        if (serialNumber >= 9999) {
            serialNumber = 1000;
        } else {
            serialNumber++;
        }
        StringBuffer result = new StringBuffer();
        result.append("SXXN");
        result.append(DateUtil.parseDateToStrDateIsNullReturnNow(null, DateUtil.FORMAT_TEMPLATE_STR_3));
        result.append(String.valueOf(serialNumber));
        return result.toString();
    }
	/**
	 * <p>
	 * 转换字符串的自定位置字母为大写
	 * </p>
	 * 
	 * @param str
	 * @param index
	 * @return
	 */
	public static String toUpperCaseFirstChar(String str, int index) {
		if (isEmpty(str)) {
			return null;
		}
		if (index < 0) {
			return str;
		}
		char[] tempArray = str.toCharArray();
		if (null == tempArray || tempArray.length < 0
				|| index > tempArray.length) {
			return null;
		}
		tempArray[index] = Character.toUpperCase(tempArray[index]);
		return String.valueOf(tempArray);
	}

	/**
	 * <p>
	 * 转换字符串的自定位置字母为小写
	 * </p>
	 * 
	 * @param str
	 * @param index
	 * @return
	 */
	public static String toLowerCaseFirstChar(String str, int index) {
		if (isEmpty(str)) {
			return null;
		}
		if (index < 0) {
			return str;
		}
		char[] tempArray = str.toCharArray();
		if (null == tempArray || tempArray.length < 0
				|| index > tempArray.length) {
			return null;
		}
		tempArray[index] = Character.toLowerCase(tempArray[index]);

		new String();
		return String.valueOf(tempArray);
	}

}
