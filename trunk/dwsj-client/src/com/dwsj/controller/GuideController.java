package com.dwsj.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dwsj.model.User;
import com.dwsj.utils.ModelUtils;
import com.dwsj.utils.Utils;
import com.dwsj.ws.GuidePortTypeProxy;

@Controller
public class GuideController {

	private GuidePortTypeProxy guideProxy = new GuidePortTypeProxy();

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/placeInfo")
	public String placeInfo(@RequestParam(value = "notify", defaultValue = "false") boolean notify,
			@RequestParam(value = "placeId", defaultValue = "0") int placeId,
			@RequestParam(value = "redirect", defaultValue = "0") int redirect,
			Model model) {
		try {
			String place = guideProxy.placeInfo(placeId);
			JSONArray array = new JSONArray(place);
			JSONObject status = array.getJSONObject(0);
			if (status != null && status.getInt("status") == 1) {
				model.addAttribute("place", ModelUtils.parsePlace(array.getJSONObject(1)));
				String listImges = guideProxy.listImagesByPlace(placeId);
				JSONArray images = new JSONArray(listImges);
				JSONObject sta = images.getJSONObject(0);
				if (sta != null && sta.getInt("status") == 1) {
					model.addAttribute("images", ModelUtils.parseImages(images));
				}
			}
			model.addAttribute("notify", notify);
			model.addAttribute("redirect", redirect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "placeInfo";
	}

	@RequestMapping("/addImage")
	public String addImage(@RequestParam(value = "placeImage", required = true) MultipartFile placeImage,
			@RequestParam(value = "placeId", defaultValue = "0", required = true) int placeId,
			@RequestParam(value = "information", required = true) String information, HttpServletRequest request) {
		boolean notify = false;
		try {
			if(!placeImage.isEmpty() && placeId > 0){
				String fileName = Utils.getTypeOfImage(placeImage.getOriginalFilename());
				User user = (User) request.getSession().getAttribute("user");
				int add = guideProxy.addImageAndInfo(user.getId(), placeId, placeImage.getBytes(), fileName, information);
				if(add > 0){
					notify = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:placeInfo?placeId=" + placeId + "&notify=" + notify +"&redirect=" + 1;
	}
	
	@RequestMapping("/commentOfImage")
	public String commentOfImage(@RequestParam(value = "imageId", defaultValue = "0") int imageId,
			Model model) {
		System.out.println("commentOfImage : " + imageId);
		return "commentOfImage";
	}
	
}
