package com.dwsj.main;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import com.dwsj.ws.DWSJServive;

public class Test {
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
		DWSJServive servive = new DWSJServive();
		System.out.println(servive.uploadImage(Test.read("./images/aaa.png"), "png"));
	}
}
