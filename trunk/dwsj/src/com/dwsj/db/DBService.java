package com.dwsj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dwsj.model.Image;
import com.dwsj.model.Place;
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
	
	public static Place getPlaceById(int placeId) {
		Place place = null;
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_PLACES WHERE ID=?");
			sta.setInt(1, placeId);
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				place = new Place();
				place.setId(re.getInt("id"));
				place.setUserId(re.getInt("dwsj_user"));
				place.setName(re.getString("place_name"));
				place.setDescription(re.getString("place_description"));
				place.setCreateDate(re.getTimestamp("create_date"));
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
		return place;
	}
	
	public static int insertImage(int placeId, int userId, String imageUrl, String information) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_IMAGES (dwsj_place,dwsj_user,image_url,image_information,create_date) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sta.setInt(1, placeId);
			sta.setInt(2, userId);
			sta.setString(3, imageUrl);
			sta.setString(4, information);
			sta.setTimestamp(5, Utils.createTimestamp());
			sta.executeUpdate();
			ResultSet insert = sta.getGeneratedKeys();
			if (insert.next()){
			    return insert.getInt(1);
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
		return 0;
	}
	
	public static boolean deleteImageAndInfo(int imageId) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("DELETE FROM DWSJ_IMAGES WHERE ID=?");
			sta.setInt(1, imageId);
			int delete = sta.executeUpdate();
			if(delete > 0) return true;
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
	
	public static boolean updateImageAndInfo(int imageId, String information) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("UPDATE DWSJ_IMAGES SET image_information=? WHERE ID=?");
			sta.setString(1, information);
			sta.setInt(2, imageId);
			int upadte = sta.executeUpdate();
			if(upadte > 0) return true;
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
	
	public static List<Image> listImageByPlace(int placeId) {
		List<Image> result = new ArrayList<Image>();
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_IMAGES WHERE DWSJ_PLACE=?");
			sta.setInt(1, placeId);
			ResultSet re = sta.executeQuery();
			Image image = null;
			while (re.next()) {
				image = new Image();
				image.setId(re.getInt("id"));
				image.setPlaceId(re.getInt("dwsj_place"));
				image.setUserId(re.getInt("dwsj_user"));
				image.setImageUrl(re.getString("image_url"));
				image.setInformation(re.getString("image_information"));
				image.setCreateDate(re.getTimestamp("create_date"));
				result.add(image);
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
		return result;
	}
}

