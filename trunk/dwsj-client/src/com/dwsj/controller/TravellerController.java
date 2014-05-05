package com.dwsj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwsj.model.User;
import com.dwsj.utils.ModelUtils;
import com.dwsj.utils.Utils;
import com.dwsj.ws.TravellerPortTypeProxy;

@Controller
public class TravellerController {
	private TravellerPortTypeProxy travellerProxy = new TravellerPortTypeProxy();

	@RequestMapping("/search")
	public String searchPlaces(@RequestParam(value = "search") String search, Model model) {
		try {
			if (StringUtils.isBlank(search)) {
				return "redirect:home";
			}
			String searchResult = travellerProxy.searchPlace(search);
			JSONArray array = new JSONArray(searchResult);
			JSONObject status = array.getJSONObject(0);
			if (status != null && status.getInt("status") == 1) {
				model.addAttribute("places", ModelUtils.parsePlaces(array));
			}
			model.addAttribute("search", search);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "home";
	}
	
	@RequestMapping("/commentOfImage")
	public String commentOfImage(@RequestParam(value = "imageId", defaultValue = "0") int imageId,
			Model model) {
		try {
			String json = travellerProxy.listCommentByImage(imageId);
			JSONArray array = new JSONArray(json);
			JSONObject status = array.getJSONObject(0);
			if (status != null && status.getInt("status") == 1) {
				model.addAttribute("comments", ModelUtils.parseComments(array));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "commentOfImage";
	}
	
	@RequestMapping("/commentOnImage")
	public void commentOnImage(
			@RequestParam(value = "imageId", defaultValue = "0") int imageId,
			@RequestParam(value = "commentData") String commentData,
			HttpServletResponse response, HttpServletRequest request){
		JSONStringer stringer = new JSONStringer();
		try {
			if(imageId <= 0 && StringUtils.isBlank(commentData)){
				stringer.object().key("status").value(0).endObject();
				response.getWriter().write(stringer.toString());
				return;
			}
			User user = (User) request.getSession().getAttribute("user");
			int comment = travellerProxy.commentOnImage(user.getId(), imageId, commentData);
			if(comment > 1){
				stringer.object().key("status").value(1).key("imageId").value(imageId).endObject();
				response.getWriter().write(stringer.toString());
				return;
			} else {
				stringer.object().key("status").value(0).endObject();
				response.getWriter().write(stringer.toString());
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				stringer.object().key("status").value(0).endObject();
				response.getWriter().write(stringer.toString());
				return;
			} catch (Exception e1) {
			}
		}
	}
	
	@RequestMapping("/rateOfImage")
	public String rateOfImage(@RequestParam(value = "imageId", defaultValue = "0") int imageId,
			Model model) {
		try {
			String json = travellerProxy.listRateByImage(imageId);
			JSONArray array = new JSONArray(json);
			JSONObject status = array.getJSONObject(0);
			if (status != null && status.getInt("status") == 1) {
				model.addAttribute("rates", Utils.collectRates(ModelUtils.parseRates(array)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rateOfImage";
	}
	
	@RequestMapping("/rateOnImage")
	public void rateOnImage(
			@RequestParam(value = "rateImageId", defaultValue = "0") int rateImageId,
			@RequestParam(value = "rating") int rating,
			HttpServletResponse response, HttpServletRequest request){
		JSONStringer stringer = new JSONStringer();
		try {
			if(rateImageId <= 0 && rating <= 0){
				stringer.object().key("status").value(0).endObject();
				response.getWriter().write(stringer.toString());
				return;
			}
			User user = (User) request.getSession().getAttribute("user");
			int rate = travellerProxy.rateOnImage(user.getId(), rateImageId, rating);
			if(rate > 0){
				stringer.object().key("status").value(1).key("imageId").value(rateImageId).endObject();
				response.getWriter().write(stringer.toString());
				return;
			} else {
				stringer.object().key("status").value(0).endObject();
				response.getWriter().write(stringer.toString());
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				stringer.object().key("status").value(0).endObject();
				response.getWriter().write(stringer.toString());
				return;
			} catch (Exception e1) {
			}
		}
	}
}
