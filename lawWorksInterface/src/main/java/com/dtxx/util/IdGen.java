package com.dtxx.util;

import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * ID算法的工具类
 * 
 * @author Hoperun
 *
 */
@Service
@Lazy(false)
public class IdGen {

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
