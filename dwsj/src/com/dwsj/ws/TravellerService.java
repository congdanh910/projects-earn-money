package com.dwsj.ws;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONStringer;

import com.dwsj.db.DBService;
import com.dwsj.model.Comment;
import com.dwsj.model.Image;
import com.dwsj.model.Information;
import com.dwsj.model.Place;
import com.dwsj.model.Rate;
import com.dwsj.model.User;
import com.dwsj.utils.Constant;
import com.dwsj.utils.Utils;

public class TravellerService {

	/*
	 * 1 	: request success 
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
				User user = DBService.loginById(pl.getUserId());
				result.object();
				result.key("id").value(pl.getId());
				result.key("user_id").value(user != null? user.getId():0);
				result.key("name").value(pl.getName());
				result.key("description").value(pl.getDescription());
				result.key("place_image").value(Utils.getImageUrl(Utils.getHttpRequest()) + pl.getPlaceImage());
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
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -1	: exception
	 * -6	: place not found
	 */
	
	public String listCommentByImage(int imageId) {
		JSONStringer result = new JSONStringer();
		try {
			result.array();
			if (imageId <= 0) {
				result.object();
				result.key("status").value(Constant.PARAMETER_FAIL);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			Image image = DBService.findImageById(imageId);
			if (image == null) {
				result.object();
				result.key("status").value(Constant.PLACE_NOT_FOUND);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			List<Comment> comments = DBService.findCommentByImage(imageId);
			result.object();
			result.key("status").value(Constant.SUCCESS);
			result.endObject();
			
			for (Comment comment : comments) {
				result.object();
				User user = DBService.loginById(comment.getUserId());
				result.key("id").value(comment.getId());
				result.key("comment").value(comment.getComment());
				result.key("user_id").value(comment.getUserId());
				result.key("user_name").value(user != null ? user.getUsername():"");
				result.key("full_name").value(user != null ? user.getFullName():"");
				result.key("image_id").value(comment.getImageId());
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
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -1	: exception
	 * -6	: place not found
	 */
	
	public String listRateByImage(int imageId) {
		JSONStringer result = new JSONStringer();
		try {
			result.array();
			if (imageId <= 0) {
				result.object();
				result.key("status").value(Constant.PARAMETER_FAIL);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			Image image = DBService.findImageById(imageId);
			if (image == null) {
				result.object();
				result.key("status").value(Constant.PLACE_NOT_FOUND);
				result.endObject();
				result.endArray();
				return result.toString();
			}
			List<Rate> rates = DBService.findRateByImage(imageId);
			result.object();
			result.key("status").value(Constant.SUCCESS);
			result.endObject();
			
			for (Rate rate : rates) {
				result.object();
				User user = DBService.loginById(rate.getUserId());
				result.key("id").value(rate.getId());
				result.key("rate").value(rate.getRate());
				result.key("user_id").value(rate.getUserId());
				result.key("user_name").value(user != null ? user.getUsername():"");
				result.key("full_name").value(user != null ? user.getFullName():"");
				result.key("image_id").value(rate.getImageId());
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
	
	/*
	 * 1 	: request success 
	 * 0 	: request fail 
	 * -3	: parameter is not good
	 * -1	: exception
	 */
	public int checkRate(int userId, int imageId){
		try {
			if(userId <= 0 || imageId <= 0)
				return Constant.PARAMETER_FAIL;
			boolean checkRate = DBService.checkRated(userId, imageId);
			if(checkRate){
				return Constant.SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return Constant.FAIL;
	}
}
