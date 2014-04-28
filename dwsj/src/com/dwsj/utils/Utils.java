package com.dwsj.utils;

import org.apache.commons.lang.StringUtils;

public class Utils {

	public static boolean checkTypes(String type) {
		if (StringUtils.isBlank(type)) {
			return false;
		}
		for (String accept : Constant.TYPES) {
			if (accept.equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}
	
	public static java.sql.Timestamp createTimestamp() {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		return new java.sql.Timestamp((calendar.getTime()).getTime());
	}
}
