package com.dwsj.main;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import com.dwsj.db.DBService;
import com.dwsj.utils.ConfigurationService;
import com.dwsj.utils.Utils;
import com.dwsj.ws.GuideServive;
import com.dwsj.ws.TravellerService;

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
			inputStream.close();
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
//		System.out.println(servive.addPlace(1, "TPHCM", "Hello HCM CITY"));
//		System.out.println(servive.addImageAndInfo(1, 3, read("./images/aaa.png"), "png", "My Image was take at HCM CITY"));
//		System.out.println(servive.deleteImageAndInfo(8));
//		System.out.println(servive.updateImageAndInfo(9, "Update image information"));
//		System.out.println(servive.listImagesByPlace(3));
//		System.out.println(servive.addInformation(1, 1, "Information"));
//		System.out.println(servive.deleteInformation(0));
//		System.out.println(servive.updateInformation(26, "Update information"));
		
		
		TravellerService travellerService = new TravellerService();
//		System.out.println(travellerService.searchPlace(""));
//		System.out.println(travellerService.rateOnImage(1, 134, 4));
//		System.out.println(travellerService.rateOnInformation(77, 26, 4));
//		System.out.println(travellerService.commentOnImage(1, 13, "commentOnImage"));
//		System.out.println(travellerService.commentOnInformation(1, 26, "commentOnInformation"));
		
//		System.out.println(DBService.rateOnImage(1, 13, 2));
//		System.out.println(DBService.rateOnInformation(1, 26, 4));
//		System.out.println(DBService.commentOnImage(1, 13, "commentOnImage"));
//		System.out.println(DBService.commentOnInformation(1, 26, "commentOnInformation"));
//		System.out.println(DBService.findInformationById(26));
//		System.out.println(DBService.findImageById(13));
//		System.out.println(DBService.findPlaceById(0));
//		System.out.println(DBService.findPlaceByUser(2).size());
	}
}
