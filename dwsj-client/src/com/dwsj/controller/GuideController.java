package com.dwsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
	
	@RequestMapping("/myPlace")
	public String myPlace(@RequestParam(value="redirect", defaultValue = "0")int redirect,
			@RequestParam(value="notify", defaultValue = "false")boolean notify,
			HttpServletRequest request, Model model){
		try {
			User user = (User) request.getSession().getAttribute("user");
			String searchResult = guideProxy.searchPlaceByUser(user.getId());
			JSONArray array = new JSONArray(searchResult);
			JSONObject status = array.getJSONObject(0);
			if (status != null && status.getInt("status") == 1) {
				model.addAttribute("places", ModelUtils.parsePlaces(array));
			}
			model.addAttribute("redirect", redirect);
			model.addAttribute("notify", notify);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "myPlace";
	}
	
	@RequestMapping("/deleteImage")
	public void deleteImage(
			@RequestParam(value = "imageId", defaultValue = "0") int imageId,
			HttpServletResponse response) {
		try {
			if(imageId <= 0){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			int delete = guideProxy.deleteImageAndInfo(imageId);
			if(delete <= 0){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
	
	@RequestMapping("/addPlace")
	public String addPlace(@RequestParam(value="placeName", required=true)String placeName,
			@RequestParam(value="placeInformation", required=true)String placeInformation,
			HttpServletRequest request){
		boolean notify = false;
		try {
			User user = (User) request.getSession().getAttribute("user");
			int addPlace = guideProxy.addPlace(user.getId(), placeName, placeInformation);
			if(addPlace > 0){
				notify = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:myPlace?redirect=1&notify=" + notify;
	}
}
