package com.dwsj.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dwsj.model.Comment;
import com.dwsj.model.Image;
import com.dwsj.model.Place;
import com.dwsj.model.Rate;
import com.dwsj.model.User;

public class ModelUtils {
	public static User parseUser(JSONObject json) {
		User result = null;
		if (json == null) {
			return null;
		}
		try {
			result = new User();
			result.setId(json.getInt("id"));
			result.setUsername(json.getString("username"));
			result.setPassword(json.getString("password"));
			result.setFullName(json.getString("full_name"));
			result.setGuide(json.getInt("guide"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static List<Place> parsePlaces(JSONArray array) {
		List<Place> result = new ArrayList<Place>();
		Place place = null;
		try {
			if(array == null){
				return result;
			}
			for (int i = 1; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				place = new Place();
				place.setId(object.getInt("id"));
				place.setUserId(object.getInt("user_id"));
				place.setName(object.getString("name"));
				place.setDescription(object.getString("description"));
				place.setPlaceImage(object.getString("place_image"));
				result.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Image> parseImages(JSONArray array) {
		List<Image> result = new ArrayList<Image>();
		Image image = null;
		try {
			if(array == null){
				return result;
			}
			for (int i = 1; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				image = new Image();
				image.setId(object.getInt("id"));
				image.setUserId(object.getInt("user_id"));
				image.setUrl(object.getString("image"));
				image.setCountComment(object.getInt("count_comment"));
				image.setCountRate(object.getInt("count_rate"));
				image.setInformation(object.getString("information"));
				result.add(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Place parsePlace(JSONObject json) {
		Place result = null;
		if (json == null) {
			return null;
		}
		try {
			result = new Place();
			result.setId(json.getInt("id"));
			result.setUserId(json.getInt("user_id"));
			result.setName(json.getString("name"));
			result.setDescription(json.getString("description"));
			result.setPlaceImage(json.getString("place_image"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static List<Comment> parseComments(JSONArray array) {
		List<Comment> result = new ArrayList<Comment>();
		Comment comment = null;
		try {
			if(array == null){
				return result;
			}
			for (int i = 1; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				comment = new Comment();
				comment.setId(object.getInt("id"));
				comment.setComment(object.getString("comment"));
				comment.setUserId(object.getInt("user_id"));
				comment.setUserName(object.getString("user_name"));
				comment.setFullName(object.getString("full_name"));
				comment.setImageId(object.getInt("image_id"));
				result.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Rate> parseRates(JSONArray array) {
		List<Rate> result = new ArrayList<Rate>();
		Rate rate = null;
		try {
			if(array == null){
				return result;
			}
			for (int i = 1; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				rate = new Rate();
				rate.setId(object.getInt("id"));
				rate.setRate(object.getInt("rate"));
				rate.setUserId(object.getInt("user_id"));
				rate.setUserName(object.getString("user_name"));
				rate.setFullName(object.getString("full_name"));
				rate.setImageId(object.getInt("image_id"));
				result.add(rate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
