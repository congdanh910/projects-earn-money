package com.dwsj.main;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import com.dwsj.utils.ConfigurationService;
import com.dwsj.ws.GuideServive;

public class Test {
	private static ConfigurationService service = ConfigurationService.getInstance();
	
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
			return outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	public static void main(String[] args) {
		GuideServive servive = new GuideServive();
//		System.out.println(servive.login("dwsj", "dwsj"));
//		System.out.println(servive.register("dwsj1", "dwsj1", "dwsj1"));
		System.out.println(servive.addPlace(1, "TPHCM", "Hello HCM CITY"));
	}
}
