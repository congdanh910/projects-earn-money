package com.dwsj.utils;

import org.apache.commons.lang3.StringUtils;

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
}
