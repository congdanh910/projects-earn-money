package com.dwsj.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwsj.utils.ModelUtils;
import com.dwsj.ws.GuidePortTypeProxy;

@Controller
public class LoginController {
	private GuidePortTypeProxy guideProxy = new GuidePortTypeProxy();

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, Model model,
			HttpServletRequest request) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			model.addAttribute("error", true);
			return "home";
		}
		try {
			String login = guideProxy.login(username, password);
			JSONArray array = new JSONArray(login);
			JSONObject status = array.getJSONObject(0);
			if (status != null && status.getInt("status") == 1) {
				request.getSession().setAttribute("login", true);
				request.getSession().setAttribute("user", ModelUtils.parseUser(array.getJSONObject(1)));
			} else {
				request.getSession().setAttribute("login", false);
			}
		} catch (Exception e) {
			model.addAttribute("exception", true);
		}
		return "redirect:home";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:home";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/registerAction")
	public String registerAction(@RequestParam(value="username", required=true)String username,
			@RequestParam(value="password", required=true)String password,
			@RequestParam(value="fullName", required=true)String fullName,
			Model model, HttpServletRequest request) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)
				|| StringUtils.isBlank(fullName)) {
			model.addAttribute("error", true);
		}
		try {
			int id = guideProxy.register(username, password, fullName);
			if (id > 0) {
				String login = guideProxy.login(username, password);
				JSONArray array = new JSONArray(login);
				JSONObject status = array.getJSONObject(0);
				if (status != null && status.getInt("status") == 1) {
					request.getSession().setAttribute("login", true);
					request.getSession().setAttribute("user", ModelUtils.parseUser(array.getJSONObject(1)));
				} else {
					request.getSession().setAttribute("login", false);
				}
			} else {
				model.addAttribute("error", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:home";
	}
}
