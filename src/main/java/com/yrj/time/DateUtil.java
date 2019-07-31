package com.yrj.time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;

import com.google.common.collect.Maps;
import com.yrj.annotation.NotNull;

/**
 * 日期工具类.
 * 
 * 在不方便使用joda-time时，使用本类降低Date处理的复杂度与性能消耗, 封装Common Lang及移植Jodd的最常用日期方法
 */
public class DateUtil {
	
	public static final String YEAR_MONTH_DAY_TEMPLATE = "yyyy-MM-dd";
	public static final String HOUR_MINUTE_SECOND_TEMPLATE = "HH:mm:ss";
	public static final String HOUR_MINUTE = "HH:mm";
	public static final String YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
	public static final String YEAR_MONTH_DAY_24HOUR= "yyyy年 MM月 dd日  HH时 mm分 ss秒 ";
	public static final String YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE_2 = "yyyyMMddHHmmss";
	
	public static final long MILLIS_PER_SECOND = 1000; // Number of milliseconds in a standard second.
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND; // Number of milliseconds in a standard minute.
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE; // Number of milliseconds in a standard hour.
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR; // Number of milliseconds in a standard day.

	private static final int[] MONTH_LENGTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	//////// 日期比较 ///////////
	/**
	 * 是否同一天.
	 * 
	 * @see DateUtils#isSameDay(Date, Date)
	 */
	public static boolean isSameDay(@NotNull final Date date1, @NotNull final Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 是否同一时刻.
	 */
	public static boolean isSameTime(@NotNull final Date date1, @NotNull final Date date2) {
		// date.getMillisOf() 比date.getTime()快
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断日期是否在范围内，包含相等的日期
	 */
	public static boolean isBetween(@NotNull final Date date, @NotNull final Date start, @NotNull final Date end) {
		if (date == null || start == null || end == null || start.after(end)) {
			throw new IllegalArgumentException("some date parameters is null or dateBein after dateEnd");
		}
		return !date.before(start) && !date.after(end);
	}

	//////////// 往前往后滚动时间//////////////
	
	
	/**
	 * 
	 * @Title: addTimeByYears
	 * @Description: TODO
	 * @param date
	 * @param amount
	 * @return
	 * @author huchenghao
	 */
	public static Date addTimeByYears(@NotNull final Date date, int amount) {
		return DateUtils.addYears(date, amount);
	}
	

	/**
	 * 加一月
	 */
	public static Date addMonths(@NotNull final Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/**
	 * 减一月
	 */
	public static Date subMonths(@NotNull final Date date, int amount) {
		return DateUtils.addMonths(date, -amount);
	}

	/**
	 * 加一周
	 */
	public static Date addWeeks(@NotNull final Date date, int amount) {
		return DateUtils.addWeeks(date, amount);
	}

	/**
	 * 减一周
	 */
	public static Date subWeeks(@NotNull final Date date, int amount) {
		return DateUtils.addWeeks(date, -amount);
	}

	/**
	 * 加一天
	 */
	public static Date addDays(@NotNull final Date date, final int amount) {
		return DateUtils.addDays(date, amount);
	}

	/**
	 * 减一天
	 */
	public static Date subDays(@NotNull final Date date, int amount) {
		return DateUtils.addDays(date, -amount);
	}

	/**
	 * 加一小时
	 */
	public static Date addHours(@NotNull final Date date, int amount) {
		return DateUtils.addHours(date, amount);
	}

	/**
	 * 减一小时
	 */
	public static Date subHours(@NotNull final Date date, int amount) {
		return DateUtils.addHours(date, -amount);
	}

	/**
	 * 加分钟
	 */
	public static Date addMinutes(@NotNull final Date date, int amount) {
		return DateUtils.addMinutes(date, amount);
	}

	/**
	 * 减一分钟
	 */
	public static Date subMinutes(@NotNull final Date date, int amount) {
		return DateUtils.addMinutes(date, -amount);
	}

	/**
	 * 终于到了，续一秒.
	 */
	public static Date addSeconds(@NotNull final Date date, int amount) {
		return DateUtils.addSeconds(date, amount);
	}

	/**
	 * 减一秒.
	 */
	public static Date subSeconds(@NotNull final Date date, int amount) {
		return DateUtils.addSeconds(date, -amount);
	}

	//////////// 直接设置时间//////////////

	/**
	 * 设置年份, 公元纪年.
	 */
	public static Date setYears(@NotNull final Date date, int amount) {
		return DateUtils.setYears(date, amount);
	}

	/**
	 * 设置月份, 0-11.
	 */
	public static Date setMonths(@NotNull final Date date, int amount) {
		return DateUtils.setMonths(date, amount);
	}

	/**
	 * 设置日期, 1-31.
	 */
	public static Date setDays(@NotNull final Date date, int amount) {
		return DateUtils.setDays(date, amount);
	}

	/**
	 * 设置小时, 0-23.
	 */
	public static Date setHours(@NotNull final Date date, int amount) {
		return DateUtils.setHours(date, amount);
	}

	/**
	 * 设置分钟, 0-59.
	 */
	public static Date setMinutes(@NotNull final Date date, int amount) {
		return DateUtils.setMinutes(date, amount);
	}

	/**
	 * 设置秒, 0-59.
	 */
	public static Date setSeconds(@NotNull final Date date, int amount) {
		return DateUtils.setSeconds(date, amount);
	}

	/**
	 * 设置毫秒.
	 */
	public static Date setMilliseconds(@NotNull final Date date, int amount) {
		return DateUtils.setMilliseconds(date, amount);
	}

	///// 获取日期的位置//////
	/**
	 * 获得日期是一周的第几天. 已改为中国习惯，1 是Monday，而不是Sundays.
	 */
	public static int getDayOfWeek(@NotNull final Date date) {
		int result = getWithMondayFirst(date, Calendar.DAY_OF_WEEK);
		return result == 1 ? 7 : result - 1;
	}

	/**
	 * 获得日期是一年的第几天，返回值从1开始
	 */
	public static int getDayOfYear(@NotNull final Date date) {
		return get(date, Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获得日期是一月的第几周，返回值从1开始.
	 * 
	 * 开始的一周，只要有一天在那个月里都算. 已改为中国习惯，1 是Monday，而不是Sunday
	 */
	public static int getWeekOfMonth(@NotNull final Date date) {
		return getWithMondayFirst(date, Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获得日期是一年的第几周，返回值从1开始.
	 * 
	 * 开始的一周，只要有一天在那一年里都算.已改为中国习惯，1 是Monday，而不是Sunday
	 */
	public static int getWeekOfYear(@NotNull final Date date) {
		return getWithMondayFirst(date, Calendar.WEEK_OF_YEAR);
	}

	private static int get(final Date date, int field) {
		Validate.notNull(date, "The date must not be null");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(field);
	}

	private static int getWithMondayFirst(final Date date, int field) {
		Validate.notNull(date, "The date must not be null");
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		return cal.get(field);
	}

	///// 获得往前往后的日期//////

	/**
	 * 2016-11-10 07:33:23, 则返回2016-1-1 00:00:00
	 */
	public static Date beginOfYear(@NotNull final Date date) {
		return DateUtils.truncate(date, Calendar.YEAR);
	}

	/**
	 * 2016-11-10 07:33:23, 则返回2016-12-31 23:59:59.999
	 */
	public static Date endOfYear(@NotNull final Date date) {
		return new Date(nextYear(date).getTime() - 1);
	}

	/**
	 * 2016-11-10 07:33:23, 则返回2017-1-1 00:00:00
	 */
	public static Date nextYear(@NotNull final Date date) {
		return DateUtils.ceiling(date, Calendar.YEAR);
	}

	/**
	 * 2016-11-10 07:33:23, 则返回2016-11-1 00:00:00
	 */
	public static Date beginOfMonth(@NotNull final Date date) {
		return DateUtils.truncate(date, Calendar.MONTH);
	}

	/**
	 * 2016-11-10 07:33:23, 则返回2016-11-30 23:59:59.999
	 */
	public static Date endOfMonth(@NotNull final Date date) {
		return new Date(nextMonth(date).getTime() - 1);
	}

	/**
	 * 2016-11-10 07:33:23, 则返回2016-12-1 00:00:00
	 */
	public static Date nextMonth(@NotNull final Date date) {
		return DateUtils.ceiling(date, Calendar.MONTH);
	}

	/**
	 * 2017-1-20 07:33:23, 则返回2017-1-16 00:00:00
	 */
	public static Date beginOfWeek(@NotNull final Date date) {
		return DateUtils.truncate(DateUtil.subDays(date, DateUtil.getDayOfWeek(date) - 1), Calendar.DATE);
	}

	/**
	 * 2017-1-20 07:33:23, 则返回2017-1-22 23:59:59.999
	 */
	public static Date endOfWeek(@NotNull final Date date) {
		return new Date(nextWeek(date).getTime() - 1);
	}

	/**
	 * 2017-1-23 07:33:23, 则返回2017-1-22 00:00:00
	 */
	public static Date nextWeek(@NotNull final Date date) {
		return DateUtils.truncate(DateUtil.addDays(date, 8 - DateUtil.getDayOfWeek(date)), Calendar.DATE);
	}

	/**
	 * 2016-11-10 07:33:23, 则返回2016-11-10 00:00:00
	 */
	public static Date beginOfDate(@NotNull final Date date) {
		return DateUtils.truncate(date, Calendar.DATE);
	}

	/**
	 * 2017-1-23 07:33:23, 则返回2017-1-23 23:59:59.999
	 */
	public static Date endOfDate(@NotNull final Date date) {
		return new Date(nextDate(date).getTime() - 1);
	}

	/**
	 * 2016-11-10 07:33:23, 则返回2016-11-11 00:00:00
	 */
	public static Date nextDate(@NotNull final Date date) {
		return DateUtils.ceiling(date, Calendar.DATE);
	}

	/**
	 * 2016-12-10 07:33:23, 则返回2016-12-10 07:00:00
	 */
	public static Date beginOfHour(@NotNull final Date date) {
		return DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 2017-1-23 07:33:23, 则返回2017-1-23 07:59:59.999
	 */
	public static Date endOfHour(@NotNull final Date date) {
		return new Date(nextHour(date).getTime() - 1);
	}

	/**
	 * 2016-12-10 07:33:23, 则返回2016-12-10 08:00:00
	 */
	public static Date nextHour(@NotNull final Date date) {
		return DateUtils.ceiling(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 2016-12-10 07:33:23, 则返回2016-12-10 07:33:00
	 */
	public static Date beginOfMinute(@NotNull final Date date) {
		return DateUtils.truncate(date, Calendar.MINUTE);
	}

	/**
	 * 2017-1-23 07:33:23, 则返回2017-1-23 07:33:59.999
	 */
	public static Date endOfMinute(@NotNull final Date date) {
		return new Date(nextMinute(date).getTime() - 1);
	}

	/**
	 * 2016-12-10 07:33:23, 则返回2016-12-10 07:34:00
	 */
	public static Date nextMinute(@NotNull final Date date) {
		return DateUtils.ceiling(date, Calendar.MINUTE);
	}

	////// 闰年及每月天数///////
	/**
	 * 是否闰年.
	 */
	public static boolean isLeapYear(@NotNull final Date date) {
		return isLeapYear(get(date, Calendar.YEAR));
	}

	/**
	 * 是否闰年，copy from Jodd Core的TimeUtil
	 * 
	 * 参数是公元计数, 如2016
	 */
	public static boolean isLeapYear(int y) {
		boolean result = false;

		if (((y % 4) == 0) && // must be divisible by 4...
				((y < 1582) || // and either before reform year...
						((y % 100) != 0) || // or not a century...
						((y % 400) == 0))) { // or a multiple of 400...
			result = true; // for leap year.
		}
		return result;
	}

	/**
	 * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
	 */
	public static int getMonthLength(@NotNull final Date date) {
		int year = get(date, Calendar.YEAR);
		int month = get(date, Calendar.MONTH);
		return getMonthLength(year, month);
	}

	/**
	 * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
	 */
	public static int getMonthLength(int year, int month) {

		if ((month < 1) || (month > 12)) {
			throw new IllegalArgumentException("Invalid month: " + month);
		}
		if (month == 2) {
			return isLeapYear(year) ? 29 : 28;
		}

		return MONTH_LENGTH[month];
	}
	
	/**
	 * 
	* @Title: parseYMDHMSStrToDate 
	* @Description: YMDHMSStr转换为Date 
	* @param @param dateStr
	* @param @return
	* @param @throws ParseException    设定文件 
	* @return Date    返回类型 Date
	* @throws
	 */
	public static Date parseYMDHMSStrToDate(String dateStr) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		return simpleDateFormat.parse(dateStr);
	}
	/**
	 * 
	 * @Title: parseYMDStrToDate
	 * @Description: YMDStr转换为Date 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 * @author huchenghao
	 * @date: 2019年1月11日 下午4:38:57
	 */
	public static Date parseYMDStrToDate(String dateStr) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YEAR_MONTH_DAY_TEMPLATE);
		return simpleDateFormat.parse(dateStr);
	}
	
	
	/**
	 * @Title: formatDateToYMDHMSStr
	 * @Description: date转换成YYYY-MM-DD HH:MM:SS格式的字符串
	 * @param date
	 * @return
	 * @author huchenghao
	 */
	public static String formatDateToYMDHMSStr(Date date){
		SimpleDateFormat df = new SimpleDateFormat(YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		return df.format(date);
	}
	/**
	 * 
	 * @Title: parseDateToYMDArray
	 * @Description: yyyy-MM-dd 转换成年月日数组
	 * @param date
	 * @return
	 * @author huchenghao
	 */
	public static String[] parseDateToYMDArray(Date date){
		String date_str = formatDateToYMDHMSStr(date);
		return date_str.split("-");
	}
	
	/**
	 * 
	 * @Title: formatDateToYMD
	 * @Description: date转换成YYYY-MM-DD格式的字符串
	 * @param date
	 * @return
	 * @author huchenghao
	 */
	public static String formatDateToYMD(Date date){
		SimpleDateFormat df = new SimpleDateFormat(YEAR_MONTH_DAY_TEMPLATE);
		return df.format(date);
	}
	
	/**
	 * 根据传入规则,格式化日期为字符串
	 * @Title: formatDate
	 * @Description: TODO
	 * @param date
	 * @param format
	 * @return
	 * @author gs
	 */
	public static String formatDate(Date date, String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	public static String getNowTimeToNo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String now = df.format(new Date());
		return now;
	}
	/**
	 * 
	 * @Title: timestampToDate
	 * @Description: 时间戳转日期 
	 * @param timestamp
	 * @return
	 * @author huchenghao
	 */
	public static Date timestampToDate(String timestamp){
        long time = Long.parseLong(timestamp);
        return new Date(time);
	}
	/**
	 * 
	 * @Title: twoDateDifferDay
	 * @Description: 两个日期相差N天
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author huchenghao
	 * @date: 2018年11月22日 下午5:26:38
	 */
	public static Long twoDateDifferDay(Date startDate,Date endDate){
		long nd = 1000 * 24 * 60 * 60;
	    long diff = endDate.getTime() - startDate.getTime();
		return  diff / nd;
	}
	/**
	 * 
	 * @Title: twoDateDifferHour
	 * @Description: 两个日期相差N小时
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author huchenghao
	 * @date: 2018年11月22日 下午5:27:51
	 */
	public static Long twoDateDifferHour(Date startDate,Date endDate){
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
	    long diff = endDate.getTime() - startDate.getTime();
		return diff % nd / nh;
	}
	/**
	 * @Title: twoDateDifferMinute
	 * @Description: 两个日期相差N分钟
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author huchenghao
	 * @date: 2018年11月22日 下午5:29:00
	 */
	public static Long twoDateDifferMinute(Date startDate,Date endDate){
		return (endDate.getTime()-startDate.getTime())/(1000*60);
	}
	/**
	 * 
	 * @Title: twoDateDifferSecond
	 * @Description: 两个日期相差N秒
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author huchenghao
	 * @date: 2019年1月3日 下午5:59:45
	 */
	public static Long twoDateDifferSecond(Date startDate,Date endDate){
		return (endDate.getTime()-startDate.getTime())/(1000);
	}
	
	/**
	 * 获取传入日期所属季度的最小时间
	 * @Title: belongQuarterMaxDate
	 * @Description: TODO
	 * @param date
	 * @return
	 * @author gs
	 * @date 2018年11月26日 下午5:30:03
	 */
	public static Date belongQuarterMinDate(Date date){
		int month = date.getMonth();
		Date now = new Date();
		now.setHours(0);
		now.setMinutes(0);
		now.setSeconds(0);
		now.setDate(1);
		if (month == 1 || month == 2 || month == 3) {
			now.setMonth(1-1);
		}else if (month == 4 || month == 5 || month == 6) {
			now.setMonth(4-1);
		}else if (month == 7 || month == 8 || month == 9) {
			now.setMonth(7-1);
		}else{
			now.setMonth(10-1);
		}
//		return formatDate(now,DateUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		return now;
	}
	
	/**
	 * 获取传入日期所属季度的最大时间
	 * @Title: belongQuarterMinDate
	 * @Description: TODO
	 * @param date
	 * @return
	 * @author gs
	 * @date 2018年11月26日 下午5:56:06
	 */
	public static Date belongQuarterMaxDate(Date date){
		int month = date.getMonth();
		Date now = new Date();
		now.setHours(23);
		now.setMinutes(59);
		now.setSeconds(59);
		if (month == 0 || month == 1 || month == 2) {
			now.setMonth(3-1);
			now.setDate(31);
		}else if (month == 3 || month == 4 || month == 5) {
			now.setMonth(6-1);
			now.setDate(30);
		}else if (month == 6 || month == 7 || month == 8) {
			now.setMonth(9-1);
			now.setDate(30);
		}else{
			now.setMonth(12-1);
			now.setDate(31);
		}
//		return formatDate(now,DateUtil.YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		return now;
	}
	/**
	 * 
	 * @Title: getYMDStr
	 * @Description: 获取YMD格式的当前时间字符串
	 * @return
	 * @author huchenghao
	 * @date: 2019年1月2日 下午3:27:17
	 */
	public static String getYMDStr(){
		SimpleDateFormat df = new SimpleDateFormat(YEAR_MONTH_DAY_TEMPLATE);
		return df.format(new Date());
	}
	/**
	 * 
	 * @Title: getLastWeekDate
	 * @Description: 获取当前日期的近7天
	 * @param format_str 如：yyyy-MM-dd  
	 * @return
	 * @author huchenghao
	 * @date: 2019年1月4日 下午5:58:01
	 */
	public static List<String> getLastWeekDate(String format_str) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(format_str);
		Calendar c = Calendar.getInstance();
		String today = sdf.format(c.getTime());
		list.add(today);
		c.add(Calendar.DAY_OF_YEAR, -1);
		String today_plus1 = sdf.format(c.getTime());
		list.add(today_plus1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		String today_plus2 = sdf.format(c.getTime());
		list.add(today_plus2);
		c.add(Calendar.DAY_OF_YEAR, -1);
		String today_plus3 = sdf.format(c.getTime());
		list.add(today_plus3);
		c.add(Calendar.DAY_OF_YEAR, -1);
		String today_plus4 = sdf.format(c.getTime());
		list.add(today_plus4);
		c.add(Calendar.DAY_OF_YEAR, -1);
		String today_plus5 = sdf.format(c.getTime());
		list.add(today_plus5);
		c.add(Calendar.DAY_OF_YEAR, -1);
		String today_plus6 = sdf.format(c.getTime());
		list.add(today_plus6);
		return list;
	}
	
	public static Date parseDateToHMS(String date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOUR_MINUTE_SECOND_TEMPLATE);
		return simpleDateFormat.parse(date);
	}
	
	public static Date parseDateToHM(String date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOUR_MINUTE);
		return simpleDateFormat.parse(date);
	}
	
	/**
	 * 求传入的两个时间片段有没有交集
	 * @param beginTime  开始时间
	 * @param endTime   结束时间
	 *
	 * @param biginDate  被比较的开始时间
	 * @param endDate  被比较的结束时间
	 * @return
	 * @throws Exception
	 */
	public static boolean intersection(String beginTime,String endTime,String biginDate,String endDate) throws Exception{
		DateFormat d_Fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date suspendTime = d_Fm.parse(beginTime);
		Date resumeTime = d_Fm.parse(endTime);
		
		Date leftDate= d_Fm.parse(biginDate);
		Date rightDate= d_Fm.parse(endDate);
		//判断两个时间段是否有交集
		if((suspendTime.getTime() <= leftDate.getTime() || suspendTime.getTime() <rightDate.getTime())
		&& ((resumeTime.getTime() >leftDate.getTime() || resumeTime.getTime() >= rightDate.getTime()))){
		return true;
		}else{
			return false;
		}
	}
	
	public static boolean intersectionHM(String beginTime,String endTime,String biginDate,String endDate) throws Exception{
		DateFormat d_Fm = new SimpleDateFormat(HOUR_MINUTE);
		Date suspendTime = d_Fm.parse(beginTime);
		Date resumeTime = d_Fm.parse(endTime);
		
		Date leftDate= d_Fm.parse(biginDate);
		Date rightDate= d_Fm.parse(endDate);
		//判断两个时间段是否有交集
		if((suspendTime.getTime() <= leftDate.getTime() || suspendTime.getTime() <rightDate.getTime())
		&& ((resumeTime.getTime() >leftDate.getTime() || resumeTime.getTime() >= rightDate.getTime()))){
		return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	* @Title: getNowTime 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getNowTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String now = df.format(new Date());
		return now;
	
	}
	
	/**
	 * 由 yyyy-MM-dd HH:mm:ss字符串 转换为 yyyyMMddHHmmss格式字符串
	 * @throws ParseException 
	 * @throws  
	 * 
	 */
	public static String dateStr2yyyyMMddHHmm(String dateStr) throws ParseException{
		Date parseDate = parseDate(dateStr, YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE);
		String formatDateStr = formatDate(parseDate, YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND_TEMPLATE_2);
		return formatDateStr;
	}
	
	/**
	 * 
	* @Title: parseDate 
	* @Description: 按照给定格式解析字符串为日期 
	* @param @param dateStr
	* @param @param format
	* @param @return
	* @param @throws ParseException    设定文件 
	* @return Date    返回类型 
	* @throws
	 */
	public static Date parseDate(String dateStr,String format) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(dateStr);
		}catch (Exception e) {
			return new Date();
		}
	}
	
	public static Date addTimeByMinutes(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	
	public static double getTimeInterval(Date stime1, Date etime1, Date stime2, Date etime2) throws Exception {
        float f = 0;
        long lst = stime1.getTime();
        long let = etime1.getTime();
 
        long rst = stime2.getTime();
        long ret = etime2.getTime();
 
        if (lst > let || rst > ret) {
            throw new Exception("起始时间不能大于结束时间");
        }
 
        if (let <= rst || lst >= ret) {
            return f;
        }
 
        long[] a = {lst, let, rst, ret};
        Arrays.sort(a); //从小到大排序，取第二、第三计算
        f = a[2] - a[1];
        
 
        return (double) Math.round((f / 60000) * 100) / 100;
    }
	
	/**
	 * 获取两个时间段的交集(分钟数)
	 * @Description
	 * @param stime1
	 * @param etime1
	 * @param stime2
	 * @param etime2
	 * @return
	 * @throws Exception
	 * @author: GuoShuai
	 * @date: 2019年3月4日 下午6:55:45
	 */
	public static double getTimeIntervalHM(Date stime1, Date etime1, Date stime2, Date etime2) throws Exception {
		stime1 = parseDateToHM(formatDate(stime1, HOUR_MINUTE));
		etime1 = parseDateToHM(formatDate(etime1, HOUR_MINUTE));
		stime2 = parseDateToHM(formatDate(stime2, HOUR_MINUTE));
		etime2 = parseDateToHM(formatDate(etime2, HOUR_MINUTE));
		return getTimeIntervalYMDHMS(stime1,etime1,stime2,etime2) / 60;
    }
	
	/**
	 * 获取两个时间段交集(秒数), 日期格式yyyy-MM-dd HH:mm:ss
	 * @Description
	 * @param stime1
	 * @param etime1
	 * @param stime2
	 * @param etime2
	 * @return
	 * @author: GuoShuai
	 * @throws Exception 
	 * @date: 2019年3月4日 下午6:56:58
	 */
	public static double getTimeIntervalYMDHMS(Date stime1, Date etime1, Date stime2, Date etime2) throws Exception {
		float f = 0;
        long lst = stime1.getTime();
        long let = etime1.getTime();
 
        long rst = stime2.getTime();
        long ret = etime2.getTime();
 
        if (lst > let || rst > ret) {
            throw new Exception("璧峰鏃堕棿涓嶈兘澶т簬缁撴潫鏃堕棿");
        }
 
        if (let <= rst || lst >= ret) {
            return f;
        }
 
        long[] a = {lst, let, rst, ret};
        Arrays.sort(a); //浠庡皬鍒板ぇ鎺掑簭锛屽彇绗簩銆佺涓夎绠� 
        f = a[2] - a[1];
        
 
        return (double) Math.round((f / 1000) * 100) / 100;
	}
	
	
	public static List<Map<String, Object>> getTimeQuantumArray(String beginDate, String endDate) throws ParseException {
		Date begin = parseYMDStrToDate(beginDate);
		Date end = parseYMDStrToDate(endDate);
		if (end.getTime() < begin.getTime()) {
			throw new RuntimeException("结束时间必须大于开始时间");
		}
//		int beginYear = begin.getYear();
//		int beginMonth = begin.getMonth();
//		int beginDay = begin.getDay();
//		int endYear = end.getYear();
//		int endMonth = end.getMonth();
//		int endDay = end.getDay();
		List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = Maps.newHashMap();
		map.put("beginDate", formatDateToYMD(begin));
		retList.add(map);
//		if (beginYear == endYear && beginMonth == endMonth && beginDay == endDay) {
//			//同一天
//			return retList;
//		}
		long differenceDay = (end.getTime()-begin.getTime())/1000/60/60/24;
		for (int i = 1; i <= differenceDay; i++) {
			Date addDays = addDays(begin, i);
			Map<String,Object> addDayMap = Maps.newHashMap();
			addDayMap.put("beginDate", formatDateToYMD(addDays));
			retList.add(addDayMap);
		}
		return retList;
	}
}
