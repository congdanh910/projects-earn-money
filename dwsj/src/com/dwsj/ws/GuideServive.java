package com.dwsj.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONStringer;

import com.dwsj.db.DBService;
import com.dwsj.model.User;
import com.dwsj.utils.ConfigurationService;
import com.dwsj.utils.Constant;
import com.dwsj.utils.Utils;

public class GuideServive {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm");
	private ConfigurationService config = ConfigurationService.getInstance();
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * 3	: parameter is not good
	 * -1 	: type not accept
	 */
	public String login(String username, String password) {
		JSONStringer result = new JSONStringer();
		try {
			if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
				result.array();
				result.object();
				result.key("status").value(Constant.PARAMETER_FAIL);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			User user = DBService.login(username, password);
			if(user != null){
				result.array();
				result.object();
				result.key("status").value(Constant.SUCCESS);
				result.endObject();
				result.object();
				result.key("id").value(user.getId());
				result.key("username").value(user.getUsername());
				result.key("password").value(user.getPassword());
				result.key("full_name").value(user.getFullName());
				result.endObject();
				result.endArray();
			} else {
				result.array();
				result.object();
				result.key("status").value(Constant.FAIL);
				result.endObject();
				result.endArray();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				result.array();
				result.object();
				result.key("status").value(Constant.EXCEPTION);
				result.endObject();
				result.endArray();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * 2 	: user existed
	 * 3	: parameter is not good
	 * -1 	: type not accept
	 */
	public int register(String username, String password, String fullName) {
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(fullName)){
			return Constant.PARAMETER_FAIL;
		}
		try {
			if(DBService.checkUser(username)){
				return Constant.USER_EXISTED;
			} else {
				if(DBService.insertUser(username, password, fullName)){
					return Constant.SUCCESS;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * 3	: parameter is not good
	 * 4	: user does not exist
	 * -1 	: type not accept
	 */
	public int addPlace(int userId, String place, String description) {
		try {
			if (userId <= 0 || StringUtils.isBlank(place)) {
				return Constant.PARAMETER_FAIL;
			}
			User user = DBService.loginById(userId);
			if (user == null) {
				return Constant.USER_NOT_FOUND;
			}
			boolean insert = DBService.insertPlace(userId, place, description);
			if (insert)
				return Constant.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * -1 	: type not accept
	 */
	public int uploadImage(byte[] data, String type) {
		try {
			if (!Utils.checkTypes(type) && data.length > 0) {
				return -1;
			}
			File file = new File(config.getString("url.images") + "/" + sdf.format(new Date()) + "." + type);
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}
}
