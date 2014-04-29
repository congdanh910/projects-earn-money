package com.dwsj.ws;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONStringer;

import com.dwsj.db.DBService;
import com.dwsj.model.Image;
import com.dwsj.model.Information;
import com.dwsj.model.Place;
import com.dwsj.model.User;
import com.dwsj.utils.Constant;

public class TravellerService {

	/*
	 * Result is id of this rateOnImage
	 * >0 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -1	: exception
	 */
	public String searchPlace(String place) {
		JSONStringer result = new JSONStringer();
		try {
			result.array();
			if (StringUtils.isBlank(place)) {
				result.object();
				result.key("status").value(Constant.PARAMETER_FAIL);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			List<Place> places = DBService.searchPlace(place);
			result.object();
			result.key("status").value(Constant.SUCCESS);
			result.endObject();
			for (Place pl : places) {
				result.object();
				result.key("id").value(pl.getId());
				result.key("name").value(pl.getName());
				result.key("description").value(pl.getDescription());
				result.endObject();
			}
			result.endArray();
		} catch (Exception ex) {
			ex.printStackTrace();
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

	/*
	 * Result is id of this rateOnImage
	 * >0 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -4	: user does not exist
	 * -7	: image does not exist
	 * -1	: exception
	 */
	public int rateOnImage(int userId, int imageId, int rate){
		try {
			if(userId <= 0 || imageId <= 0  || rate <=0 || rate > 5)
				return Constant.PARAMETER_FAIL;
			User user = DBService.loginById(userId);
			if(user == null)
				return Constant.USER_NOT_FOUND;
			Image image = DBService.findImageById(imageId);
			if(image == null)
				return Constant.IMAGE_NOT_FOUND;
			int insert = DBService.rateOnImage(userId, imageId, rate);
			if(insert > 0){
				return insert;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * Result is id of this rateOnImage
	 * >0 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -4	: user does not exist
	 * -8	: information does not exist
	 * -1	: exception
	 */
	public int rateOnInformation(int userId, int infoId, int rate){
		try {
			if(userId <= 0 || infoId <= 0  || rate <=0 || rate > 5)
				return Constant.PARAMETER_FAIL;
			User user = DBService.loginById(userId);
			if(user == null)
				return Constant.USER_NOT_FOUND;
			Information information = DBService.findInformationById(infoId);
			if(information == null)
				return Constant.INFOR_NOT_FOUND;
			int insert = DBService.rateOnInformation(userId, infoId, rate);
			if(insert > 0){
				return insert;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * Result is id of this rateOnImage
	 * >0 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -4	: user does not exist
	 * -7	: image does not exist
	 * -1	: exception
	 */
	public int commentOnImage(int userId, int imageId, String comment){
		try {
			if(userId <= 0 || imageId <= 0  || StringUtils.isBlank(comment))
				return Constant.PARAMETER_FAIL;
			User user = DBService.loginById(userId);
			if(user == null)
				return Constant.USER_NOT_FOUND;
			Image image = DBService.findImageById(imageId);
			if(image == null)
				return Constant.IMAGE_NOT_FOUND;
			int insert = DBService.commentOnImage(userId, imageId, comment);
			if(insert > 0){
				return insert;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
	
	/*
	 * Result is id of this rateOnImage
	 * >0 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -4	: user does not exist
	 * -8	: information does not exist
	 * -1	: exception
	 */
	public int commentOnInformation(int userId, int infoId, String comment){
		try {
			if(userId <= 0 || infoId <= 0  || StringUtils.isBlank(comment))
				return Constant.PARAMETER_FAIL;
			User user = DBService.loginById(userId);
			if(user == null)
				return Constant.USER_NOT_FOUND;
			Information information = DBService.findInformationById(infoId);
			if(information == null)
				return Constant.INFOR_NOT_FOUND;
			int insert = DBService.commentOnInformation(userId, infoId, comment);
			if(insert > 0){
				return insert;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
}
