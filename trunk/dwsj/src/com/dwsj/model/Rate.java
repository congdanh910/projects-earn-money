package com.dwsj.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int imageId;
	private int rate;
	private Timestamp createDate;

	public Rate() {
	}

	public Rate(int id, int userId, int imageId, int rate, Timestamp createDate) {
		this.id = id;
		this.userId = userId;
		this.imageId = imageId;
		this.rate = rate;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
