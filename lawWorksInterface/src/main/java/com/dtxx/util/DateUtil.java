package com.dtxx.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * java.util.Date工具类.
 * </p>
 * 
 * @author Wang_Jie
 * @Date 2013-09-04
 * 
 */
public class DateUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7275229305728849043L;
	/**
	 * <p>
	 * 默认模板,格式：yyyyMMdd
	 * </p>
	 */
	public static final String DEFAULT_FORMAT_TEMPLATE = "yyyyMMdd";
	/**
	 * <p>
	 * 格式化模板,格式：yyyyMMdd
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE = "yyyyMMdd";
	/**
	 * <p>
	 * 格式化模板,格式：yyyyMMddHHmmss
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR = "yyyyMMddHHmmss";
	/**
	 * <p>
	 * 格式化模板,格式：yyyy-MM-dd
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR_1 = "yyyy-MM-dd";
	/**
	 * <p>
	 * 格式化模板,格式：yyyy年MM月dd日HH:mm:ss
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR_2 = "yyyy年MM月dd日HH:mm:ss";
	/**
	 * <p>
	 * 格式化模板,格式：yyyyMMddHHmmss
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR_3 = "yyyyMMddHHmmss";
	/**
	 * <p>
	 * 格式化模板,格式：yyyy年MM月dd日
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR_4 = "yyyy年MM月dd日";
	/**
	 * <p>
	 * 格式化模板,格式：yyyyMMddHHmmssSSS
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR_5 = "yyyyMMddHHmmssSSS";

	/**
	 * <p>
	 * 格式化模板,格式：yyyy年MM月dd日HH时mm分ss秒SSS毫秒
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR_6 = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
	/**
	 * <p>
	 * 格式化模板,格式：yyyy-MM-dd HH:mm:ss
	 * </p>
	 */
	public static final String FORMAT_TEMPLATE_STR_7 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * <p>
	 * 日期格式成字符串,默认日期当前日期,默认格式:yyyyMMdd。
	 * </p>
	 * 
	 * @param date
	 * @param template
	 * @return
	 * @throws java.text.ParseException
	 */
	public static String parseDateToStrDateIsNullReturnNow(Date date,
			String template) throws ParseException {

		if (StringUtil.isEmpty(template)) {
			template = DateUtil.DEFAULT_FORMAT_TEMPLATE;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);

		if (null == date) {
			return simpleDateFormat.format(new Date());
		}

		return simpleDateFormat.format(date);
	}

	/**
	 * <p>
	 * 日期格式成字符串,默认格式:yyyyMMdd。
	 * </p>
	 * 
	 * @param date
	 * @param template
	 * @return
	 * @throws java.text.ParseException
	 */
	public static String parseDateToString(Date date, String template)
			throws ParseException {
		if (StringUtil.isEmpty(template)) {
			template = DateUtil.DEFAULT_FORMAT_TEMPLATE;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);
		if (null == date) {
			return null;
		}
		return simpleDateFormat.format(date);
	}

	/**
	 * <p>
	 * 指定格式的字符串转换成Date,默认格式:yyyyMMdd。
	 * </p>
	 * 
	 * @param str
	 * @param template
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringToDate(String str, String template)
			throws ParseException {

		if (StringUtil.isEmpty(str)) {
			return null;
		}

		if (StringUtil.isEmpty(template)) {
			template = DateUtil.DEFAULT_FORMAT_TEMPLATE;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);

		return simpleDateFormat.parse(str);
	}

	/**
	 * <p>
	 * 获取指定日期当前月的某一天.
	 * </p>
	 * 
	 * @param date
	 * @param appointDay
	 * @return
	 */
	public static Date getMonthAppointDay(Date date, int appointDay) {
		if (null == date) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, appointDay);

		return calendar.getTime();
	}

	/**
	 * <p>
	 * 指定日期是一星期中的第几天.
	 * </p>
	 * 
	 * @param date
	 * @return -1 表示date 为空.
	 */
	public static int getDayOfWeek(Date date) {
		if (null == date) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * <p>
	 * java.util.Date 转换成 java.sql.Date
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date parseDateToSQLDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null == date) {
			calendar.setTime(new Date());
			return new java.sql.Date(calendar.getTimeInMillis());
		}
		calendar.setTime(date);
		return new java.sql.Date(calendar.getTimeInMillis());
	}

	/**
	 * <p>
	 * 给Date新增指定的月数,如果给定的时间为null返回 NullPointerException
	 * </p>
	 * 
	 * @param date
	 *            日期
	 * @param amount
	 *            指定月数.
	 * @return Date
	 */
	public static Date addFixedMonth(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (null == date) {
			throw new NullPointerException(DateUtil.class.getName());
		}
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}

	/**
	 * <p>
	 * 给Date新增指定的年数,如果给定的时间为null返回 NullPointerException
	 * </p>
	 * 
	 * @param date
	 *            日期
	 * @param amount
	 *            指定月数.
	 * @return Date
	 */
	public static Date addFixedYear(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (null == date) {
			throw new NullPointerException(DateUtil.class.getName());
		}
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, amount);
		return calendar.getTime();
	}

	/**
	 * <p>
	 * 给Date新增指定的天数,如果给定的时间为null返回 NullPointerException
	 * </p>
	 * 
	 * @param date
	 *            日期
	 * @param amount
	 *            指定月数.
	 * @return Date
	 */
	public static Date addFixedDay(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (null == date) {
			throw new NullPointerException(DateUtil.class.getName());
		}
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return calendar.getTime();

	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date addFieldAndAmount(Date date, int field, int amount) {

		Calendar calendar = Calendar.getInstance();
		if (null == date) {
			throw new NullPointerException(DateUtil.class.getName());
		}
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

}
