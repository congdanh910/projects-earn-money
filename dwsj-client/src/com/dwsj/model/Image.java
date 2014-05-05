package com.dwsj.model;

import java.io.Serializable;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int placeId;
	private int userId;
	private int countComment;
	private int countRate;
	private String url;
	private String information;

	public Image() {
	}

	public Image(int id, int placeId, int userId, int countComment,
			int countRate, String url, String information) {
		this.id = id;
		this.placeId = placeId;
		this.userId = userId;
		this.countComment = countComment;
		this.countRate = countRate;
		this.url = url;
		this.information = information;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCountComment() {
		return countComment;
	}

	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}

	public int getCountRate() {
		return countRate;
	}

	public void setCountRate(int countRate) {
		this.countRate = countRate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}
