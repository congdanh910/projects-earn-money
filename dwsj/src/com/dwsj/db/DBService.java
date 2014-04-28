package com.dwsj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dwsj.model.User;
import com.dwsj.utils.Utils;

public class DBService {
	
	public static User loginById(int id) {
		User user = null;
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_USERS WHERE ID=?");
			sta.setInt(1, id);
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				user = new User();
				user.setId(re.getInt("id"));
				user.setUsername(re.getString("username"));
				user.setPassword(re.getString("password"));
				user.setFullName(re.getString("full_name"));
				user.setCreateDate(re.getTimestamp("create_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(sta != null) sta.close();
					if(connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return user;
	}
	
	public static User login(String username, String password) {
		User user = null;
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_USERS WHERE USERNAME=? AND PASSWORD=?");
			sta.setString(1, username);
			sta.setString(2, password);
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				user = new User();
				user.setId(re.getInt("id"));
				user.setUsername(re.getString("username"));
				user.setPassword(re.getString("password"));
				user.setFullName(re.getString("full_name"));
				user.setCreateDate(re.getTimestamp("create_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(sta != null) sta.close();
					if(connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return user;
	}
	
	public static boolean checkUser(String username) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT COUNT(*) AS COUNT_USER FROM DWSJ_USERS WHERE USERNAME=?");
			sta.setString(1, username);
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				int count = re.getInt("COUNT_USER");
				if (count > 0)
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(sta != null) sta.close();
					if(connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return false;
	}
	
	
	public static boolean insertUser(String username, String password, String fullName) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_USERS(username,password,full_name,create_date) VALUES (?,?,?,?)");
			sta.setString(1, username);
			sta.setString(2, password);
			sta.setString(3, fullName);
			sta.setTimestamp(4, Utils.createTimestamp());
			int insert = sta.executeUpdate();
			if(insert > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(sta != null) sta.close();
					if(connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return false;
	}
	
	public static boolean insertPlace(int userId, String place, String description) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_PLACES(dwsj_user,place_name,place_description,create_date) VALUES (?,?,?,?)");
			sta.setInt(1, userId);
			sta.setString(2, place);
			sta.setString(3, description);
			sta.setTimestamp(4, Utils.createTimestamp());
			int insert = sta.executeUpdate();
			if(insert > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				try {
					if(sta != null) sta.close();
					if(connection != null) connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return false;
	}
}

