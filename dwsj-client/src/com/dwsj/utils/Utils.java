package com.dwsj.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;

public class Utils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
	private static ConfigurationService config = ConfigurationService.getInstance();

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


	public static byte[] read(String path) {
		byte[] re = null;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			FileInputStream inputStream = new FileInputStream(path);
			int len = 0;
			byte[] buffer = new byte[512];
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			return outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	public static String getTypeOfImage(String imageurl) {
		if (StringUtils.isBlank(imageurl) && !imageurl.contains(".")) {
			return null;
		}
		return imageurl.substring(imageurl.lastIndexOf(".") + 1, imageurl.length());
	}
}
