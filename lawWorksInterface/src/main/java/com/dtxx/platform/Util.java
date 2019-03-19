/** 
 * @ProjectName:  MainActivity 
 * @FileName:  	  Util.java 
 * @PackageName:  com.bdtj.application 
 * @Date:         2016年5月9日下午4:51:03 
 * @Copyright:    Copyright (c) 2016, Loong All Rights Reserved. 
 * 
 */
package com.dtxx.platform;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: Util <br/>
 * 
 * @Description TODO
 *
 * @date: 2016年5月9日 下午4:51:03 <br/>
 * @version
 * @since JDK 1.6
 */
public class Util {

	/**
	 * 关闭流对象
	 * 
	 * @param stream
	 */
	public static void safeClose(Closeable stream) {
		if (null != stream) {
			try {
				if (stream instanceof OutputStream) {
					OutputStream outputStream = (OutputStream) stream;
					outputStream.flush();
				}
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
