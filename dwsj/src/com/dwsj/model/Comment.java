package com.dwsj.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private int imageId;
	private String comment;
	private Timestamp createDate;

	public Comment() {
	}

	public Comment(int id, int userId, int imageId, String comment,
			Timestamp createDate) {
		this.id = id;
		this.userId = userId;
		this.imageId = imageId;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
