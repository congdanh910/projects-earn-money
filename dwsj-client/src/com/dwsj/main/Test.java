package com.dwsj.main;

import org.json.JSONArray;

import com.dwsj.utils.ConfigurationService;
import com.dwsj.utils.ModelUtils;
import com.dwsj.utils.Utils;
import com.dwsj.ws.GuidePortTypeProxy;
import com.dwsj.ws.TravellerPortTypeProxy;

public class Test {
	private static ConfigurationService service = ConfigurationService.getInstance();
	private static GuidePortTypeProxy guideProxy = new GuidePortTypeProxy();
	private static TravellerPortTypeProxy travellerProxy = new TravellerPortTypeProxy();
	public static void main(String[] args) {
		try {
//			System.out.println(guideProxy.listImagesByPlace(1));
//			System.out.println(travellerProxy.listCommentByImage(13));
			String json = travellerProxy.listRateByImage(13);
			JSONArray array = new JSONArray(json);
			System.out.println(Utils.collectRates(ModelUtils.parseRates(array)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
