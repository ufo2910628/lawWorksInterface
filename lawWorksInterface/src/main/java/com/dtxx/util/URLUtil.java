/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtxx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
//import org.apache.log4j.Logger;

/**
 *
 * @author wangliang
 */
public class URLUtil {
    //private static final Logger log = Logger.getLogger(URLUtil.class);

    private int num = 0;

    public String getInfoUTF8(String url) throws Exception{
        return getInfo(url, "UTF-8");
    }

    public String getInfoGB(String url) throws Exception{
        return getInfo(url, "GBK");
    }

    public String getInfoGB2312(String url) throws Exception{
        return getInfo(url, "GB2312");
    }

    private String getInfo(String url, String charsetName) throws Exception{
        try {
            StringBuffer codeBuffer = null;
            BufferedReader in = null;
            URLConnection uc = new URL(url).openConnection();
            uc.setConnectTimeout(5000);

            uc.setReadTimeout(100000);
            /**
             * 为了限制客户端不通过网页直接读取网页内容,就限制只能从浏览器提交请求.
             * 但是我们可以通过修改http头的User-Agent来伪装,这个代码就是这个作用
             *
             */
            uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
            String ce = uc.getContentEncoding();

            if (ce != null && ce.contains("zip")) {
                // 读取url流内容
                File f = new File("temp.xml");

                GZIPInputStream gzin = new GZIPInputStream(uc.getInputStream());

                FileOutputStream fout = new FileOutputStream(f);
                byte[] buf = new byte[1024];
                int num;
                while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                    fout.write(buf, 0, num);
                }
                gzin.close();
                fout.close();
                codeBuffer = new StringBuffer();
                codeBuffer.append(read(new FileInputStream(f), charsetName));
                f.delete();
            } else {
                // 读取url流内容
                in = new BufferedReader(new InputStreamReader(uc.getInputStream(), charsetName));
                codeBuffer = new StringBuffer();
                String tempCode = "";
                // 把buffer内的值读取出来,保存到code中
                while ((tempCode = in.readLine()) != null) {
                    codeBuffer.append(tempCode);
                }
                in.close();
            }
            return codeBuffer.toString();
        } catch (Exception ex) {
            //log.info("访问url时异常！！！");
            return null;
        }
//        return "";
    }

    private String read(FileInputStream in, String charsetName) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, charsetName));
            String line = br.readLine(); // 读取第一行
            while (line != null) {
                sb.append(line);
                line = br.readLine(); // 读取下一行
            }
            br.close();
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String getInfoTrim(String url, String encode) {
        try {
            StringBuffer codeBuffer = null;
            BufferedReader in = null;
            URLConnection uc = new URL(url).openConnection();


            /**
             * 为了限制客户端不通过网页直接读取网页内容,就限制只能从浏览器提交请求.
             * 但是我们可以通过修改http头的User-Agent来伪装,这个代码就是这个作用
             *
             */
            uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
            // 读取url流内容
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(), encode));
            codeBuffer = new StringBuffer();
            String tempCode = "";

            // 把buffer内的值读取出来,保存到code中
            while ((tempCode = in.readLine()) != null) {
                tempCode = tempCode.trim();
                codeBuffer.append(tempCode);
            }
            in.close();
            return codeBuffer.toString();
        } catch (Exception ex) {
            num++;
            if (num < 8) {
                return getInfoTrim(url, encode);
            }
        }
        return "";
    }

    public String replaceSpecialCharacter(String str) {

        String[] needReplace = {"&quot;", "&amp;", "&lt;", "&gt;", "&nbsp;"};

        for (String s : needReplace) {
            if (s.equals("&quot;")) {
                str = str.replace(s, "\"");
            }
            if (s.equals("&amp;")) {
                str = str.replace(s, "&");
            }
            if (s.equals("&lt;")) {
                str = str.replace(s, "<");
            }
            if (s.equals("&gt;")) {
                str = str.replace(s, ">");
            }
            if (s.equals("&nbsp;")) {
                str = str.replace(s, " ");
            }

        }
        return str;
    }


}
