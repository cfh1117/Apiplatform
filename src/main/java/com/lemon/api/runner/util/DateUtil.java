package com.lemon.api.runner.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static SimpleDateFormat yyyy_mm_dd_hh_mm_ss_formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat yyyy_mm_dd = new SimpleDateFormat("yyyy-MM-dd");
	
	/**获取yyyy-MM-dd HH:mm:ss格式的日期
	 * @param date
	 * @return
	 */
	public static String formatYmdhms(Date date){
		String dataString = yyyy_mm_dd_hh_mm_ss_formater.format(new Date());
		return dataString;
	}
	
	/**获取yyyy-MM-dd格式的日期
	 * @param date
	 * @return
	 */
	public static String formatYmd(Date date){
		String dataString = yyyy_mm_dd.format(new Date());
		return dataString;
	}
	
	
}
