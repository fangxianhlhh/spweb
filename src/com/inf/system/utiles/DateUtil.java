package com.inf.system.utiles;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期操作相关方法
 * @author Administrator
 *
 */
public class DateUtil {

	/**
	 * CD-001格式化日期
	 * @param date
	 *  @param date
	 * @return pattern 日期格式
	 */
	public static String strDateFormat(String date,String pattern) {
		// 时间格式化
		String r_date = "";
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			Date date1 = df.parse(date);
			r_date = df.format(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return r_date;
	}

	/**
	 * CD-002格式化日期
	 * @param date  Date 日期
	 * @return String 输出字符串
	 */
	public static String DateFormatStr(Date date,String pattern) {
		if (date == null) {
			return "";
		}else{
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
	}

	/**
	 * CD-003获得当前时间
	 * @return String
	 */
	public static String getNowDate(String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}

	/**
	 * CD-004获得指定时间 n天的时间
	 * 
	 * @return String
	 */
	public static String getPreviousDate(Date date, Integer days,String pattern) {
		Calendar cal = Calendar.getInstance();
		if(null != date){
			cal.setTime(date);
		}
		cal.add(Calendar.DATE, days);
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(cal.getTime());
	}

	 //获取指定日期后多少天的时间
	 public  static  Date getafterDate(Date date, Integer days){
		 Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		 aCalendar.setTime(date);
		 aCalendar.add(Calendar.DATE, days);
		 return aCalendar.getTime();
		 
	 }
	 

	/**
	 * CD-006获得指定年份月分的第一天日期和最后一天日期
	 * 
	 * @param year   int 年份
	 * @param month     int 月份
	 * @return String[]
	 */
	public static String[] getMonth(int year, int month) {
		SimpleDateFormat format = new SimpleDateFormat(Constant.FORMAT_DATE);
		String[] dates = new String[2];
		Calendar startCal = Calendar.getInstance();
		startCal.set(year, month - 1, 1);
		dates[0] = format.format(startCal.getTime());

		Calendar endCal = Calendar.getInstance();
		endCal.set(year, month, 1);
		endCal.add(Calendar.DATE, -1);
		dates[1] = format.format(endCal.getTime());
		return dates;
	}

	/**
	 * CD-008 获得两个日期之间的天数日期格式为yyyy-MM-dd
	 * 
	 * @param startTime 起始日期
	 * @param endTime  结束日期
	 * @return int
	 */
	public static int getDateFromTime1ToTime2(String startTime, String endTime) {
		SimpleDateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE_TIME);
		Date dt1 = null;
		Date dt2 = null;
		int checkDays = 0;
		try {
			dt1 = df.parse(endTime);
			dt2 = df.parse(startTime);
			long l = dt1.getTime() - dt2.getTime();
			l = (long) (l / (1000 * 60 * 60 * 24) + 0.5);
			checkDays = Integer.parseInt(String.valueOf(l));
		} catch (ParseException e) {
			return checkDays;
		}
		return checkDays;
	}

	/**
	 * CD-009 将日期转换为毫秒:
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static long dateToMilliSecond(String date, String pattern) {
		// 传入的参数要与yyyyMMddHH的格式相同 "yyyyMMddHH"
		SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
		Date date2 = null;
		try {
			date2 = df.parse(date);// 将参数按照给定的格式解析参数
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2.getTime();
	}

	/**
	 * CD-010日期加减
	 * 
	 * @param inday    输入的时间
	 * @param offset  日期间隔天数
	 * @return 日期（yyyy-MM-dd）
	 */
	public static String changeByDay(String inday, int offset) {
		Calendar calendar = Calendar.getInstance();
		Date date = strToDate(inday);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		SimpleDateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		String day = df.format(calendar.getTime());
		return day;
	}

	/**
	 * CD-011 日期转换 把String 类型转换为date类型
	 * 
	 * @param inday    输入的时间
	 * @param offset   日期间隔天数
	 * @return 日期（yyyy-MM-dd）
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.FORMAT_DATE);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * CD-012 获取本月的第一天，格式为yyyy-MM-dd
	 * 
	 * @return 从现在到上周的时间字符串
	 */
	public static String getfirstDayOfMonth() {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		
		return df.format(ca.getTime());
	}

	/**
	 * CD-013 年加减
	 * @param sDate 输入的时间
	 * @param offset    日期间隔月数
	 * @return 日期（yyyy-MM-dd）
	 */
	public static String getChangYears(String sDate, int offset) {
		Calendar calendar = Calendar.getInstance();
		Date date = strToDate(sDate);
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, offset);
		SimpleDateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		return df.format(calendar.getTime());

	}

	/**
	 * CD-014 获得本月的最后一天
	 * @return String
	 */
	public static String getLastMonthDay(String sDate) {
		SimpleDateFormat df = new SimpleDateFormat(Constant.FORMAT_DATE);
		Date d = strToDate(sDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return  df.format(calendar.getTime());
	}

	/**
	 * CD-015 得到指定年月的天数
	 * */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	
   /**
	 * 获取上个月的年份
	 * @param date Date格式的日期
	 * @return 返回年份
	*/
  public static Integer beforeMonthYear(Date date){
	 Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
	 aCalendar.setTime(date);
	 aCalendar.add(Calendar.MONTH,-1);  //上个月时间
	 return aCalendar.get(Calendar.YEAR);
 }
 
/**
 * 获取上个月的月份
 * @param date
 * @return
 */
 public static Integer beforeMonth(Date date){
	 Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
	 aCalendar.setTime(date);
	 aCalendar.add(Calendar.MONTH,-1);  //上个月时间
	 return aCalendar.get(Calendar.MONTH)+1;
 }
 

 //获取指定时间所在月份开始时间
 public static Date  getBeginTime(Date date){
	 Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
	 aCalendar.setTime(date);
	 aCalendar.set(Calendar.DAY_OF_MONTH, 1);
	 aCalendar.set(Calendar.HOUR_OF_DAY, 0);  
	 aCalendar.set(Calendar.MINUTE, 0);  
	 aCalendar.set(Calendar.SECOND, 0);  
	 return aCalendar.getTime();
 }
 
 //获取指定时间的所在月份的最后时间
 public static Date getEndTimes(Date date){
	 
	 Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
	 aCalendar.setTime(date);
	 aCalendar.set(Calendar.DAY_OF_MONTH, 1);
	 aCalendar.add(Calendar.MONTH, 1);
	 aCalendar.add(Calendar.DATE, -1);
	 aCalendar.set(Calendar.HOUR_OF_DAY, 23);  
	 aCalendar.set(Calendar.MINUTE, 59);  
	 aCalendar.set(Calendar.SECOND, 59);  
	 return aCalendar.getTime();
	 
 }
 
 //获取指定时间（天）的开始开始时间
 public static Date getTimesBegin(Date date){
	 Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
	 aCalendar.setTime(date);
	 aCalendar.set(Calendar.HOUR_OF_DAY, 0);  
	 aCalendar.set(Calendar.MINUTE, 0);  
	 aCalendar.set(Calendar.SECOND, 0);  
	 return aCalendar.getTime();
	 
	 
 }
 
 //获取指定时间（天）的结束时间
 public static Date getTimesEnd(Date date){
	 
	 Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
	 aCalendar.setTime(date);
	 aCalendar.set(Calendar.HOUR_OF_DAY, 23);  
	 aCalendar.set(Calendar.MINUTE, 59);  
	 aCalendar.set(Calendar.SECOND, 59); 
	 return aCalendar.getTime();
	 
 }
 
 
//测试
public static void main(String[] args) {
	// DateUtil du= new DateUtil();
	/*du.getTimesBegin(new Date());
   du.getTimesEnd(new Date());*/
	 // du.getEndTimes(new Date());
}
 
 
}
