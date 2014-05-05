package com.dwsj.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dwsj.model.Image;
import com.dwsj.model.Place;
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
				place.setName(object.getString("name"));
				place.setDescription(object.getString("description"));
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
				image.setUrl(object.getString("image"));
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
			result.setName(json.getString("name"));
			result.setDescription(json.getString("description"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
