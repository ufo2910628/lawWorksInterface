package com.dtxx.util;

import javax.servlet.http.HttpServletRequest;

import com.dtxx.model.dto.ReturnInfoJson;
import com.dtxx.platform.ServerEncodeTest;

public class ReturnUtil {
	public static String returnEncodeData(HttpServletRequest request,String date){
		try {
			String OutPublicKey=(String) request.getAttribute("OutPublicKey");
			String AesKey1= (String) request.getAttribute("AesKey1");
			String AesKey2=(String) request.getAttribute("AesKey2");
			return ServerEncodeTest.encodeData(date, OutPublicKey,AesKey1,AesKey2 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
