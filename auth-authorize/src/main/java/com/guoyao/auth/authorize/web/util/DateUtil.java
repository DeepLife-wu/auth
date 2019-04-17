/**
 * 
 */
package com.guoyao.auth.authorize.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wuchao
 * @Date 【2019年2月18日:下午5:32:37】
 */
public class DateUtil {
	public static final SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat ymdHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * <b>获取指定的开始日期,例如:2019-02-18 17:35:00,返回2019-01-18 00:00:00</b>
	 * @param startDate
	 * @return
	 */
	public static Date getStartDate(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(
			calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DATE),
		0, 0, 0);
		startDate = calendar.getTime();
		return startDate;
	}
	
	/**
	 * <b>获取指定的开始日期,例如:2019-02-18 17:35:00,返回2019-01-18 59:59:59</b>
	 * @param startDate
	 * @return
	 */
	public static Date getEndDate(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(
			calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH),
			calendar.get(Calendar.DATE),
		23, 59, 59);
		startDate = calendar.getTime();
		return startDate;
	}
}
