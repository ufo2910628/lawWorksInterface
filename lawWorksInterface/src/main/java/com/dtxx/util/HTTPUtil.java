package com.dtxx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

//import org.apache.log4j.Logger;

public class HTTPUtil implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5513207642345610957L;

    //private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * <p>
     * <P>
     * 模拟HTTP GET请求订购.
     * </P>
     * </p>
     *
     * @param urlStr 链接路径
     * @param fileName 文件名称、全路径
     * @return String 返回处理结果.
     * @throws Exception 异常.
     */
    public String connectionHTTPByGet(String urlStr) throws Exception {
        //this.logger.debug("urlStr : " + urlStr);
        // 建立URL链接.
        URL url = new URL(urlStr);
        HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
        url_con.setRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        url_con.setConnectTimeout(5000);
        url_con.setReadTimeout(5000);
        url_con.connect();
        // 获取链接状态.
        int responseCode = url_con.getResponseCode();
        // 判断是否链接成功。200成功.
        if (responseCode != 200) {
            return null;
        }
        // 获取返回结果。
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(url_con.getInputStream(), "UTF-8"));

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

    /**
     * <P>
     * 模拟HTTP POST请求订购.
     * </P>
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    public String connectionHTTPByPost(String urlStr) throws Exception {
        //this.logger.info("urlStr : " + urlStr);

        // 建立URL链接.
        URL url = new URL(urlStr);
        HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
        url_con.setRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        url_con.setDoOutput(true);
        url_con.setDoInput(true);
        // POST必须大写。
        url_con.setRequestMethod("POST");

        url_con.setConnectTimeout(5000);
        url_con.setReadTimeout(5000);
        url_con.connect();

        // 创建输出。输入流。
        // 获取链接状态.
        int responseCode = url_con.getResponseCode();
        // 判断是否链接成功。200成功.
        if (responseCode != 200) {
            //return null;
            return String.valueOf(responseCode);
        }
        // 获取返回结果。
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(url_con.getInputStream(), "UTF-8"));

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

    /**
     * <P>
     * 模拟HTTP POST请求订购.
     * </P>
     *
     * @param urlStr
     * @return
     * @throws Exception
     */
    public String connectionHTTPByPost(String urlStr, String content) throws Exception {

        //this.logger.info("urlStr : " + urlStr + " content : " + content);
        // 建立URL链接.
        URL url = new URL(urlStr);
        HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
        url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        url_con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
        url_con.setDoOutput(true);
        url_con.setDoInput(true);
        // POST必须大写。
        url_con.setRequestMethod("POST");
        url_con.setConnectTimeout(5000);
        url_con.setReadTimeout(5000);
        url_con.connect();

        // 创建输出。输入流。
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(url_con
                .getOutputStream(), "UTF-8");

        outputStreamWriter.write(content);

        if (null != outputStreamWriter) {
            outputStreamWriter.close();
        }
        // 获取链接状态.
        int responseCode = url_con.getResponseCode();
        // 判断是否链接成功。200成功.
        if (responseCode != 200) {
            return null;
        }
        // 获取返回结果。
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(url_con.getInputStream(), "UTF-8"));

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
