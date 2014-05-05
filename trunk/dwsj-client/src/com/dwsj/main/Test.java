package com.dwsj.main;

import java.rmi.RemoteException;

import com.dwsj.utils.ConfigurationService;
import com.dwsj.ws.GuidePortTypeProxy;

public class Test {
	private static ConfigurationService service = ConfigurationService.getInstance();
	private static GuidePortTypeProxy guideProxy = new GuidePortTypeProxy();
	public static void main(String[] args) {
		try {
			System.out.println(guideProxy.listImagesByPlace(1));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
