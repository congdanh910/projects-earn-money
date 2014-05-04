package com.dwsj.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Place implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private String name;
	private String description;
	private Timestamp createDate;

	public Place(int id, int userId, String name, String description,
			Timestamp createDate) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.createDate = createDate;
	}

	public Place() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
