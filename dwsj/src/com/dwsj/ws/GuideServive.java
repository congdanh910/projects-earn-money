package com.dwsj.ws;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONStringer;

import com.dwsj.db.DBService;
import com.dwsj.model.Image;
import com.dwsj.model.Place;
import com.dwsj.model.User;
import com.dwsj.utils.Constant;
import com.dwsj.utils.Utils;

public class GuideServive {
	
	/*
	 * This function will response for client User-Information
	 * 1 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -1 	: exception
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
	 * -2 	: user existed
	 * -3	: parameter is not good
	 * -1 	: exception
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
	 * -3	: parameter is not good
	 * -4	: user does not exist
	 * -1 	: exception
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
	 * Result is id of this image
	 * >0 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -4	: user does not exist
	 * -6	: place does not exist
	 * -5 	: type not accept
	 * -1	: exception
	 */
	public int addImageAndInfo(int userId, int placeId, byte[] image,
			String type, String information) {
		try {
			if (userId <= 0 || placeId <= 0 || image == null
					|| StringUtils.isBlank(type)
					|| StringUtils.isBlank(information)) {
				return Constant.PARAMETER_FAIL;
			}
			if (!Utils.checkTypes(type)) {
				return Constant.TYPE_FAIL;
			}
			User user = DBService.loginById(userId);
			if (user == null)
				return Constant.USER_NOT_FOUND;
			Place place = DBService.getPlaceById(placeId);
			if (place == null)
				return Constant.PLACE_NOT_FOUND;
			String upload = Utils.uploadImage(image, type, Utils.getHttpRequest());
			if (StringUtils.isBlank(upload)) {
				return Constant.FAIL;
			}
			int insert = DBService.insertImage(placeId, userId, upload, information);
			if (insert > 0)
				return insert;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -1	: exception
	 */
	public int deleteImageAndInfo(int imageId) {
		try {
			if (imageId <= 0)
				return Constant.PARAMETER_FAIL;
			boolean delete = DBService.deleteImageAndInfo(imageId);
			if (delete)
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
	 * -3	: parameter is not good
	 * -1	: exception
	 */
	public int updateImageAndInfo(int imageId, String information) {
		try {
			if (imageId <= 0 || StringUtils.isBlank(information))
				return Constant.PARAMETER_FAIL;
			boolean update = DBService.updateImageAndInfo(imageId, information);
			if (update)
				return Constant.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * Result is id of this information
	 * >0 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -4	: user does not exist
	 * -6	: place does not exist
	 * -1	: exception
	 */
	public int addInformation(int userId, int placeId, String information) {
		try {
			if (userId <= 0 || placeId <= 0 || StringUtils.isBlank(information)) {
				return Constant.PARAMETER_FAIL;
			}
			
			User user = DBService.loginById(userId);
			if (user == null)
				return Constant.USER_NOT_FOUND;
			Place place = DBService.getPlaceById(placeId);
			if (place == null)
				return Constant.PLACE_NOT_FOUND;
			int insert = DBService.insertInformation(placeId, userId, information);
			if (insert > 0)
				return insert;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -1	: exception
	 */
	public int deleteInformation(int infoId) {
		try {
			if (infoId <= 0)
				return Constant.PARAMETER_FAIL;
			boolean delete = DBService.deleteInformation(infoId);
			if (delete)
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
	 * -3	: parameter is not good
	 * -1	: exception
	 */
	public int updateInformation(int infoId, String information) {
		try {
			if (infoId <= 0 || StringUtils.isBlank(information))
				return Constant.PARAMETER_FAIL;
			boolean update = DBService.updateInformation(infoId, information);
			if (update)
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
	 * -3	: parameter is not good
	 * -1	: exception
	 * -6	: place not found
	 */
	
	public String listImagesByPlace(int placeId) {
		JSONStringer result = new JSONStringer();
		try {
			result.array();
			if (placeId <= 0) {
				result.object();
				result.key("status").value(Constant.PARAMETER_FAIL);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			Place place = DBService.getPlaceById(placeId);
			if (place == null) {
				result.object();
				result.key("status").value(Constant.PLACE_NOT_FOUND);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			List<Image> images = DBService.listImageByPlace(placeId);
			result.object();
			result.key("status").value(Constant.SUCCESS);
			result.endObject();
			
			for (Image image : images) {
				result.object();
				result.key("id").value(image.getId());
				result.key("image").value(Utils.getImageUrl(Utils.getHttpRequest()) + image.getName());
				result.key("type").value(Utils.getTypeOfImage(image.getName()));
				result.key("information").value(image.getInformation());
				result.endObject();
			}
			result.endArray();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				result.array();
				result.object();
				result.key("status").value(Constant.EXCEPTION);
				result.endObject();
				result.endArray();
				return result.toString();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return result.toString();
	}
}
