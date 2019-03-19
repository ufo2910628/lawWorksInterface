package com.dtxx.platform;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	public static String encode(String str) {
		try {
			return (new BASE64Encoder()).encode(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String encode(byte[] str) {
		try {
			return (new BASE64Encoder()).encode(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] encodeBuffer(String str) {
		try {
			return new BASE64Encoder().encode(str.getBytes("UTF-8")).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String decode(String str) {
		try {
			return new String((new BASE64Decoder()).decodeBuffer(str),"UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decodeBuffer(String str) {
		try {
			return (new BASE64Decoder()).decodeBuffer(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
