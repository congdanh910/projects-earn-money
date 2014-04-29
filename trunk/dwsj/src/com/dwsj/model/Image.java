package com.dwsj.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int placeId;
	private int userId;
	private String imageUrl;
	private String information;
	private Timestamp createDate;

	public Image() {
	}

	public Image(int id, int placeId, int userId, String imageUrl,
			String information, Timestamp createDate) {
		this.id = id;
		this.placeId = placeId;
		this.userId = userId;
		this.imageUrl = imageUrl;
		this.information = information;
		this.createDate = createDate;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
