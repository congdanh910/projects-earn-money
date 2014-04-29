package com.dwsj.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
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

	public static String uploadImage(byte[] data, String type, HttpServletRequest request) {
		String re = "";
		try {
			String fileName = sdf.format(new Date()) + "." + type;
			File folder = new File(request.getServletContext().getRealPath("/")  + "/" + config.getString("image.folder"));
			if(!folder.exists()){
				folder.mkdir();
			}
			String path = request.getServletContext().getRealPath("/")  + "/" + config.getString("image.folder") + "/" + fileName;
			File file = new File(path);
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
			re = fileName;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return re;
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
		return imageurl.substring(imageurl.lastIndexOf(".") + 1,
				imageurl.length());
	}

	public static HttpServletRequest getHttpRequest() {
		HttpServletRequest request = null;
		try {
			request = ((HttpServletRequest) MessageContext.getCurrentMessageContext().getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST));
		} catch (Exception e) {
		}
		return request;
	}

	public static String getImageUrl(HttpServletRequest request) {
		if (request == null)
			return null;
		String host = request.getLocalAddr();
		int port = request.getLocalPort();
		String contextPath = request.getContextPath();
		return "http://" + host + ":" + port + contextPath +"/" + config.getString("image.folder") + "/";
	}

}
