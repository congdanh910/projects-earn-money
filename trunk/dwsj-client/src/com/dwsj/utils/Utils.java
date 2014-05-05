package com.dwsj.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwsj.model.Rate;

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

	public static Map<String, Integer> collectRates(List<Rate> rates){
		Map<String, Integer> maps = new HashMap<String, Integer>();
		maps.put("1", 0);
		maps.put("2", 0);
		maps.put("3", 0);
		maps.put("4", 0);
		maps.put("5", 0);		
		if(rates != null && rates.size() > 0){
			for (Rate rate : rates) {
				if(maps.containsKey(rate.getRate() + "")){
					maps.put(rate.getRate() + "", maps.get(rate.getRate() + "") + 1);
				} else {
					maps.put(rate.getRate() + "", 1);
				}
			}
		}
		return maps;
	}
}
