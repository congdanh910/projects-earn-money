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
}
