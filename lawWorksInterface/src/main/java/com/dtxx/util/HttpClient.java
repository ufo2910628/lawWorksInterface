/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dtxx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class HttpClient{
        
       public static String post(String urlStr, String content) throws Exception{
           return connection("POST", urlStr, content);
       }

       public static String get(String urlStr) throws Exception{
           return connection("GET", urlStr, null);
       }       
    
    
       /**
        * 
        * @param requestMethod 请求方式 GET 或 POST
        * @param urlStr 请求地址
        * @param content 通过流输出的内容或参数
        * @return responsState!=200 返回内容为null
        * @throws Exception 
        */
       public static String connection(String requestMethod,String urlStr,
		String content) throws Exception {
		// 建立URL链接.
		URL url = new URL(urlStr);
		HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
		url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                url_con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		url_con.setDoInput(true);
                url_con.setUseCaches(false);
                if("POST".equals(requestMethod))
                        url_con.setDoOutput(true);
		url_con.setRequestMethod(requestMethod);

		url_con.setConnectTimeout(5000);
		url_con.setReadTimeout(5000);
		url_con.connect();
		// 创建输出。输入流。
                if("POST".equals(requestMethod)){
                    
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(url_con
                                    .getOutputStream(), "utf-8");

                    outputStreamWriter.write(content);

                    if (null != outputStreamWriter) {
                            outputStreamWriter.close();
                    }
                }
                
                
		// 获取链接状态.
		int responseCode = url_con.getResponseCode();
		// 判断是否链接成功。200成功.
                if(responseCode==204){
                        return "sendSmsSuccess";
                }else if (responseCode != 200) {
			return null;
		} 
		// 获取返回结果。
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(url_con.getInputStream(),"utf-8"));

		StringBuffer result = new StringBuffer();

		String sCurrentLine = null;

		while (null != (sCurrentLine = bufferedReader.readLine())) {
			result.append(sCurrentLine);
		}
		// 关闭输出。输入流。
		if (null != bufferedReader) {
			bufferedReader.close();
		}
		return result.toString();
	}
}
