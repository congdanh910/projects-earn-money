package com.dwsj.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String fullName;
	private int guide;
	private Timestamp createDate;

	public User() {
	}

	public User(int id, String username, String password, String fullName,
			int guide, Timestamp createDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.guide = guide;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getGuide() {
		return guide;
	}

	public void setGuide(int guide) {
		this.guide = guide;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}