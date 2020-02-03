package com.samton.platform.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @Description:日期工具类
 * @author:     Alex
 * @date:        2017年2月23日 上午9:34:11
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class DateUtil {

	/**
	 * 
	 * @Title:        now 
	 * @Description:  获得当前时间
	 * @param:        @return    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:36:28
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 
	 * @Title:        formatDate 
	 * @Description:  时间格式化方法(yyyy-MM-dd)
	 * @param:        @param date
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:36:39
	 */
	public static String formatDate(Date date) {
		return getDateDF().format(date);
	}

	/**
	 * 
	 * @Title:        formatDatetime 
	 * @Description:  时间格式化方法(yyyy-MM-dd HH:mm:ss)
	 * @param:        @param date
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:45:04
	 */
	public static String formatDatetime(Date date) {
		return getDatetimeDF().format(date);
	}

	/**
	 * 
	 * @Title:        formatDatetimeNum 
	 * @Description:  时间格式化方法(yyyyMMddHHmmss)
	 * @param:        @param date
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:45:42
	 */
	public static String formatDatetimeNum(Date date) {
		return getDatetimeNumDF().format(date);
	}

	/**
	 * 
	 * @Title:        formatTime 
	 * @Description:  时间格式化方法(HH:mm:ss)
	 * @param:        @param date
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:46:47
	 */
	public static String formatTime(Date date) {
		return getTimeDF().format(date);
	}

	/**
	 * 
	 * @Title:        currentDate 
	 * @Description:  获得当前时间格式化(yyyy-MM-dd)
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:47:16
	 */
	public static String currentDate() {
		return formatDate(now());
	}

	/**
	 * 
	 * @Title:        currentDatetime 
	 * @Description:  获得当前时间格式化(yyyy-MM-dd HH:mm:ss)
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:48:54
	 */
	public static String currentDatetime() {
		return formatDatetime(now());
	}

	/**
	 * 
	 * @Title:        currentDatetimeNum 
	 * @Description:  获得当前时间格式化(yyyyMMddHHmmss)
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:49:30
	 */
	public static String currentDatetimeNum() {
		return formatDatetimeNum(now());
	}

	/**
	 * 
	 * @Title:        currentTime 
	 * @Description:  获得当前时间格式化(HH:mm:ss)
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:49:54
	 */
	public static String currentTime() {
		return formatTime(now());
	}

	/**
	 * 
	 * @Title:        formatDatetime 
	 * @Description:  按照指定格式时间格式化
	 * @param:        @param date		时间
	 * @param:        @param pattern	格式
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:50:27
	 */
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat patternDF = new SimpleDateFormat(pattern);
		return patternDF.format(date);
	}

	/**
	 * 
	 * @Title:        calendar 
	 * @Description:  获得中国日历
	 * @param:        @return    
	 * @return:       Calendar    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:01:06
	 */
	public static Calendar calendar() {
		// 设置日历区域
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		// 设置一周的起始日为周一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * 
	 * @Title:        millis 
	 * @Description:  获取当前系统时间
	 * @param:        @return    
	 * @return:       long    
	 * @author        Alex
	 * @Date          2017年2月23日 上午9:51:56
	 */
	public static long millis() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * @Title:        month 
	 * @Description:  获得当前月
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:02:23
	 */
	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	/**
	 * 
	 * @Title:        dayOfMonth 
	 * @Description:  获得当天在这月第几天
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:02:49
	 */
	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 
	 * @Title:        dayOfWeek 
	 * @Description:  获得当天在这周第几天
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:04:05
	 */
	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 
	 * @Title:        dayOfWeekChina 
	 * @Description:  获得当天在这周第几天(中国格式)
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:05:58
	 */
	public static int dayOfWeekChina() {
		Calendar c = calendar();
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 
	 * @Title:        dayOfYear 
	 * @Description:  获得当天在这年第几天
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:06:38
	 */
	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 
	 * @Title:        isBefore 
	 * @Description:  日期比较(src是否在dst之前)
	 * @param:        @param src
	 * @param:        @param dst
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:09:06
	 */
	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	/**
	 * 
	 * @Title:        isAfter 
	 * @Description:  日期比较(src是否在dst之后)
	 * @param:        @param src
	 * @param:        @param dst
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:11:24
	 */
	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	/**
	 * 
	 * @Title:        isEqual 
	 * @Description:  日期比较(src是否与dst一样)
	 * @param:        @param date1
	 * @param:        @param date2
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:11:40
	 */
	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 
	 * @Title:        between 
	 * @Description:  判断某个日期是否在某个日期范围
	 * @param:        @param beginDate	日期范围开始
	 * @param:        @param endDate	日期范围结束
	 * @param:        @param src		需要判断的日期
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:12:02
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 
	 * @Title:        lastDayOfMonth 
	 * @Description:  获得当前月的最后一天	HH:mm:ss为0，毫秒为999
	 * @param:        @return    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:12:23
	 */
	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		// M月置零
		cal.set(Calendar.DAY_OF_MONTH, 0);
		// H置零
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// m置零
		cal.set(Calendar.MINUTE, 0);
		// s置零
		cal.set(Calendar.SECOND, 0);
		// S置零
		cal.set(Calendar.MILLISECOND, 0);
		// 月份+1
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		// 毫秒-1
		cal.set(Calendar.MILLISECOND, -1);
		return cal.getTime();
	}

	/**
	 * 
	 * @Title:        firstDayOfMonth 
	 * @Description:  获得当前月的第一天	HH:mm:ss SS为零
	 * @param:        @return    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:13:17
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		// M月置1
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// H置零
		cal.set(Calendar.HOUR_OF_DAY, 0);
		// m置零
		cal.set(Calendar.MINUTE, 0);
		// s置零
		cal.set(Calendar.SECOND, 0);
		// S置零
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 
	 * @Title:        friday 
	 * @Description:  获得周五日期	日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * @param:        @return    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:14:11
	 */
	public static Date friday() {
		return weekDay(Calendar.FRIDAY);
	}

	/**
	 * 
	 * @Title:        saturday 
	 * @Description:  获得周六日期	日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * @param:        @return    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:14:29
	 */
	public static Date saturday() {
		return weekDay(Calendar.SATURDAY);
	}

	/**
	 * 
	 * @Title:        sunday 
	 * @Description:  获得周日日期	日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
	 * @param:        @return    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:14:48
	 */
	public static Date sunday() {
		return weekDay(Calendar.SUNDAY);
	}

	/**
	 * 
	 * @Title:        parseDatetime 
	 * @Description:  将字符串日期时间转换成java.util.Date类型	日期时间格式yyyy-MM-dd HH:mm:ss
	 * @param:        @param datetime
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:15:00
	 */
	public static Date parseDatetime(String datetime) throws ParseException {
		return getDatetimeDF().parse(datetime);
	}

	/**
	 * 
	 * @Title:        parseDate 
	 * @Description:  将字符串日期转换成java.util.Date类型	日期时间格式yyyy-MM-dd
	 * @param:        @param date
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:15:14
	 */
	public static Date parseDate(String date) throws ParseException {
		return getDateDF().parse(date);
	}

	/**
	 * 
	 * @Title:        parseDate 
	 * @Description:  将字符串日期转换成java.util.Date类型	日期时间格式yyyy-MM-dd
	 * @param:        @param date
	 * @param:        @param format
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:15:28
	 */
	public static Date parseDate(String date, String format) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	/**
	 * 
	 * @Title:        parseTime 
	 * @Description:  将字符串日期转换成java.util.Date类型 	时间格式 HH:mm:ss
	 * @param:        @param time
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:15:48
	 */
	public static Date parseTime(String time) throws ParseException {
		return getTimeDF().parse(time);
	}

	/**
	 * 
	 * @Title:        parseDatetime 
	 * @Description:  根据自定义pattern将字符串日期转换成java.util.Date类型
	 * @param:        @param datetime
	 * @param:        @param pattern
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:16:01
	 */
	public Date parseDatetime(String datetime, String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(datetime);
	}

	/**
	 * 
	 * @Title:        firstDayOfYear 
	 * @Description:  本年第一天
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:18:43
	 */
	public static String firstDayOfYear() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 1);
		return DateUtil.transferLongToDate("yyyyMMdd", c.getTimeInMillis());
	}

	/**
	 * 
	 * @Title:        lastDayOfYear 
	 * @Description:  本年最后一天
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:19:04
	 */
	public static String lastDayOfYear() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		currCal.set(Calendar.YEAR, currentYear);
		currCal.roll(Calendar.DAY_OF_YEAR, 1);
		return DateUtil.transferLongToDate("yyyyMMdd", currCal.getTimeInMillis());
	}

	public static void main(String[] args) {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		currCal.set(Calendar.YEAR, currentYear);
		currCal.roll(Calendar.DAY_OF_YEAR, 1);
		System.out.println(DateUtil.transferLongToDate("yyyyMMdd", currCal.getTimeInMillis()));

	}

	/**
	 * 
	 * @Title:        minusDay 
	 * @Description:  根据相差天数获得日期
	 * @param:        @param day				相差天数
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:16:25
	 */
	public static Date minusDay(int day) throws ParseException {
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
		Date endDate = getDateDF().parse(getDateDF().format(date.getTime()));
		return endDate;
	}

	/**
	 * 
	 * @Title:        getWeekStartEndDate 
	 * @Description:  计算当前周的第num周的第一天日期和最后一天日期
	 * @param:        @param num				当前周的第几周，可以是负数，负数即为前几周
	 * @param:        @return    
	 * @return:       Map<String,Date>    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:18:15
	 */
	public static Map<String, Date> getWeekStartEndDate(Integer num) {
		Map<String, Date> result = new HashMap<String, Date>();
		Date nowTime = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowTime);
		ca.add(Calendar.DATE, 6 * num);
		ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 当前周第一天
		Date startDate = ca.getTime();
		result.put("startDate", startDate);
		// 当前周最后一天
		ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		ca.add(Calendar.WEEK_OF_YEAR, 1);
		Date endDate = ca.getTime();
		result.put("endDate", endDate);
		return result;
	}

	/**
	 * 
	 * @Title:        getMonthStartEndDate 
	 * @Description:  计算当前月的第num月的第一天日期和最后一天日期 
	 * @param:        @param num				当前月的第几个月，可以是负数，负数即为前几个月
	 * @param:        @return    
	 * @return:       Map<String,Date>    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:19:48
	 */
	public static Map<String, Date> getMonthStartEndDate(Integer num) {
		Map<String, Date> result = new HashMap<String, Date>();
		Date nowTime = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowTime);
		ca.add(Calendar.MONTH, num);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = ca.getTime();
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = ca.getTime();
		result.put("startDate", startDate);
		result.put("endDate", endDate);
		return result;
	}

	/**
	 * 
	 * @Title:        minusDay 
	 * @Description:  计算指定日期day天前后
	 * @param:        @param date				指定日期
	 * @param:        @param day				相差天数
	 * @param:        @return
	 * @param:        @throws ParseException    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:20:35
	 */
	public static Date minusDay(Date date, int day) throws ParseException {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = date;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
		Date endDate = dft.parse(dft.format(calendar.getTime()));
		return endDate;
	}

	/**
	 * 
	 * @Title:        transferLongToDate 
	 * @Description:  把毫秒转化成日期
	 * @param:        @param dateFormat			日期格式，例如：MM/ dd/yyyy HH:mm:ss
	 * @param:        @param millSec			毫秒数
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:23:06
	 */
	public static String transferLongToDate(String dateFormat, Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	/**
	 * 
	 * @Title:        transferDateToString 
	 * @Description:  把日期转化成字符串日期
	 * @param:        @param dateFormat			日期格式，例如：MM/ dd/yyyy HH:mm:ss
	 * @param:        @param date				日期
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:23:28
	 */
	public static String transferDateToString(String dateFormat, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 
	 * @Title:        addDays 
	 * @Description:  功能：当前时间增加天数。
	 * @param:        @param date				正值时时间延后，负值时时间提前。
	 * @param:        @param days
	 * @param:        @return    
	 * @return:       Date    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:23:46
	 */
	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
		return new Date(c.getTimeInMillis());
	}

	/**
	 * 
	 * @Title:        differDays 
	 * @Description:  计算两个日期相差天数
	 * @param:        @param start
	 * @param:        @param end
	 * @param:        @return    
	 * @return:       int    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:24:22
	 */
	public static int differDays(Date start, Date end) {
		long diff = end.getTime() - start.getTime();
		return (int) (diff / (24 * 60 * 60 * 1000));
	}

	/**
	 * 
	 * @Title:        getNowYear 
	 * @Description:  本年
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:26:34
	 */
	public static String getNowYear() {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c = Calendar.getInstance();
		c.setTime(new Date());
		// 本年第一天
		c.set(Calendar.DAY_OF_YEAR, 1);
		result = result + sdf.format(c.getTime());
		int year = c.get(Calendar.YEAR);
		c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, year);
		// 本年最后一天
		c.roll(Calendar.DAY_OF_YEAR, -1);
		result = result + sdf.format(c.getTime());
		return result;
	}

	/**
	 * 
	 * @Title:        getNowMonth 
	 * @Description:  本月
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:26:50
	 */
	public static String getNowMonth() {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c = Calendar.getInstance();
		// 本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		result = result + sdf.format(c.getTime());
		// 本月最后一天
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		result = result + sdf.format(c.getTime());
		return result;
	}

	/**
	 * 
	 * @Title:        getNowWeek 
	 * @Description:  本周
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:27:08
	 */
	public static String getNowWeek() {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		// 周一
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		result = result + sdf.format(c.getTime());
		// 周日
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		result = result + sdf.format(c.getTime());
		return result;
	}

	/**
	 * 
	 * @Title:        isSameDate 
	 * @Description:  判断两个日期是否是同一天
	 * @param:        @param date1
	 * @param:        @param date2
	 * @param:        @return    
	 * @return:       boolean    
	 * @author        Alex
	 * @Date          2017年2月23日 上午10:28:01
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	private static ThreadLocal<Map<String, SimpleDateFormat>> dfThreadLocal = new ThreadLocal<Map<String, SimpleDateFormat>>() {
		protected synchronized Map<String, SimpleDateFormat> initialValue() {
			SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat datetimeNumFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			Map<String, SimpleDateFormat> map = new HashMap<String, SimpleDateFormat>();
			map.put("datetime", datetimeFormat);
			map.put("date", dateFormat);
			map.put("time", timeFormat);
			map.put("datetimeNum", datetimeNumFormat);
			return map;
		}
	};

	private static SimpleDateFormat getDatetimeDF() {
		return dfThreadLocal.get().get("datetime");
	}

	private static SimpleDateFormat getDatetimeNumDF() {
		return dfThreadLocal.get().get("datetimeNum");
	}

	private static SimpleDateFormat getDateDF() {
		return dfThreadLocal.get().get("date");
	}

	private static SimpleDateFormat getTimeDF() {
		return dfThreadLocal.get().get("time");
	}
}