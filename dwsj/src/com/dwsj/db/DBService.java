package com.dwsj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dwsj.model.Comment;
import com.dwsj.model.Image;
import com.dwsj.model.Information;
import com.dwsj.model.Place;
import com.dwsj.model.Rate;
import com.dwsj.model.User;
import com.dwsj.utils.Constant;
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
				user.setGuide(re.getInt("guide"));
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
				user.setGuide(re.getInt("guide"));
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
	
	
	public static boolean insertUser(String username, String password, String fullName, int guide) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_USERS(username,password,full_name, guide,create_date) VALUES (?,?,?,?,?)");
			sta.setString(1, username);
			sta.setString(2, password);
			sta.setString(3, fullName);
			sta.setInt(4, guide);
			sta.setTimestamp(5, Utils.createTimestamp());
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
	
	public static boolean insertPlace(int userId, String place, String description, String placeImage) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_PLACES(dwsj_user,place_name,place_description,place_image,create_date) VALUES (?,?,?,?,?)");
			sta.setInt(1, userId);
			sta.setString(2, place);
			sta.setString(3, description);
			sta.setString(4, placeImage);
			sta.setTimestamp(5, Utils.createTimestamp());
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
				place.setPlaceImage(re.getString("place_image"));
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
			sta = connection.prepareStatement("INSERT INTO DWSJ_IMAGES (dwsj_place,dwsj_user,name,image_information,create_date) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
	
	public static Image findImageById(int id) {
		Image image = null;
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_IMAGES WHERE ID=? AND STATUS=1");
			sta.setInt(1, id);
			ResultSet re = sta.executeQuery();
			if(re.next()){
				image = new Image();
				image.setId(re.getInt("id"));
				image.setPlaceId(re.getInt("dwsj_place"));
				image.setUserId(re.getInt("dwsj_user"));
				image.setName(re.getString("name"));
				image.setInformation(re.getString("image_information"));
				image.setCreateDate(re.getTimestamp("create_date"));
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
		return image;
	}
	
	public static boolean deleteImageAndInfo(int imageId) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("UPDATE DWSJ_IMAGES SET status=0 WHERE ID=?");
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
	
	public static int insertInformation(int placeId, int userId, String information) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_INFORMATION (dwsj_place,dwsj_user,information,create_date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sta.setInt(1, placeId);
			sta.setInt(2, userId);
			sta.setString(3, information);
			sta.setTimestamp(4, Utils.createTimestamp());
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
	
	public static Information findInformationById(int id) {
		Information information = null;
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_INFORMATION WHERE ID=?");
			sta.setInt(1, id);
			ResultSet re = sta.executeQuery();
			if(re.next()){
				information = new Information();
				information.setId(re.getInt("id"));
				information.setPlaceId(re.getInt("dwsj_place"));
				information.setUserId(re.getInt("dwsj_user"));
				information.setInformation(re.getString("information"));
				information.setCreateDate(re.getTimestamp("create_date"));
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
		return information;
	}
	
	public static boolean updateInformation(int infoId, String information) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("UPDATE DWSJ_INFORMATION SET information=? WHERE ID=?");
			sta.setString(1, information);
			sta.setInt(2, infoId);
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
	
	public static boolean deleteInformation(int infoId) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("DELETE FROM DWSJ_INFORMATION WHERE ID=?");
			sta.setInt(1, infoId);
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
	
	public static List<Image> listImageByPlace(int placeId) {
		List<Image> result = new ArrayList<Image>();
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_IMAGES WHERE DWSJ_PLACE=? AND STATUS=1");
			sta.setInt(1, placeId);
			ResultSet re = sta.executeQuery();
			Image image = null;
			while (re.next()) {
				image = new Image();
				image.setId(re.getInt("id"));
				image.setPlaceId(re.getInt("dwsj_place"));
				image.setUserId(re.getInt("dwsj_user"));
				image.setName(re.getString("name"));
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
	
	public static List<Place> searchPlace(String place) {
		List<Place> result = new ArrayList<Place>();
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_PLACES WHERE place_name LIKE ?");
			sta.setString(1,"%"+ place + "%");
			ResultSet re = sta.executeQuery();
			Place plac = null;
			while (re.next()) {
				plac = new Place();
				plac.setId(re.getInt("id"));
				plac.setUserId(re.getInt("dwsj_user"));
				plac.setName(re.getString("place_name"));
				plac.setDescription(re.getString("place_description"));
				plac.setPlaceImage(re.getString("place_image"));
				plac.setCreateDate(re.getTimestamp("create_date"));
				result.add(plac);
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
	
	public static Place findPlaceById(int id) {
		Place plac = null;
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_PLACES WHERE id= ?");
			sta.setInt(1, id);;
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				plac = new Place();
				plac.setId(re.getInt("id"));
				plac.setUserId(re.getInt("dwsj_user"));
				plac.setName(re.getString("place_name"));
				plac.setDescription(re.getString("place_description"));
				plac.setPlaceImage(re.getString("place_image"));
				plac.setCreateDate(re.getTimestamp("create_date"));
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
		return plac;
	}
	
	public static List<Place> findPlaceByUser(int userId) {
		List<Place> result = new ArrayList<Place>();
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_PLACES WHERE dwsj_user = ?");
			sta.setInt(1, userId);
			ResultSet re = sta.executeQuery();
			Place plac = null;
			while (re.next()) {
				plac = new Place();
				plac.setId(re.getInt("id"));
				plac.setUserId(re.getInt("dwsj_user"));
				plac.setName(re.getString("place_name"));
				plac.setDescription(re.getString("place_description"));
				plac.setPlaceImage(re.getString("place_image"));
				plac.setCreateDate(re.getTimestamp("create_date"));
				result.add(plac);
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
	
	public static List<Place> findAllPlace() {
		List<Place> result = new ArrayList<Place>();
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_PLACES");
			ResultSet re = sta.executeQuery();
			Place plac = null;
			while (re.next()) {
				plac = new Place();
				plac.setId(re.getInt("id"));
				plac.setUserId(re.getInt("dwsj_user"));
				plac.setName(re.getString("place_name"));
				plac.setDescription(re.getString("place_description"));
				plac.setPlaceImage(re.getString("place_image"));
				plac.setCreateDate(re.getTimestamp("create_date"));
				result.add(plac);
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
	
	public static int rateOnImage(int userId, int imageId, int rate) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_RATES (dwsj_user,dwsj_image,rate,create_date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sta.setInt(1, userId);
			sta.setInt(2, imageId);
			sta.setInt(3, rate);
			sta.setTimestamp(4, Utils.createTimestamp());
			sta.executeUpdate();
			ResultSet insert = sta.getGeneratedKeys();
			if (insert.next()){
			    return insert.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return Constant.EXCEPTION;
		} finally {
			try {
				if (sta != null)
					sta.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Constant.FAIL;
	}
	
	public static int rateOnInformation(int userId, int infoId, int rate) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_RATES (dwsj_user,dwsj_information,rate,create_date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sta.setInt(1, userId);
			sta.setInt(2, infoId);
			sta.setInt(3, rate);
			sta.setTimestamp(4, Utils.createTimestamp());
			sta.executeUpdate();
			ResultSet insert = sta.getGeneratedKeys();
			if (insert.next()){
			    return insert.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return Constant.EXCEPTION;
		} finally {
			try {
				if (sta != null)
					sta.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Constant.FAIL;
	}
	
	public static int commentOnImage(int userId, int imageId, String comment) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_COMMENTS (dwsj_user,dwsj_image,comment,create_date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sta.setInt(1, userId);
			sta.setInt(2, imageId);
			sta.setString(3, comment);
			sta.setTimestamp(4, Utils.createTimestamp());
			sta.executeUpdate();
			ResultSet insert = sta.getGeneratedKeys();
			if (insert.next()){
			    return insert.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return Constant.EXCEPTION;
		} finally {
			try {
				if (sta != null)
					sta.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Constant.FAIL;
	}
	
	public static int commentOnInformation(int userId, int infoId, String comment) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("INSERT INTO DWSJ_COMMENTS (dwsj_user,dwsj_information,comment,create_date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sta.setInt(1, userId);
			sta.setInt(2, infoId);
			sta.setString(3, comment);
			sta.setTimestamp(4, Utils.createTimestamp());
			sta.executeUpdate();
			ResultSet insert = sta.getGeneratedKeys();
			if (insert.next()){
			    return insert.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return Constant.EXCEPTION;
		} finally {
			try {
				if (sta != null)
					sta.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Constant.FAIL;
	}
	
	public static List<Comment> findCommentByImage(int imageId) {
		List<Comment> result = new ArrayList<Comment>();
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_COMMENTS WHERE dwsj_image = ?");
			sta.setInt(1, imageId);
			ResultSet re = sta.executeQuery();
			Comment comment = null;
			while (re.next()) {
				comment = new Comment();
				comment.setUserId(re.getInt("dwsj_user"));
				comment.setImageId(re.getInt("dwsj_image"));
				comment.setComment(re.getString("comment"));
				comment.setCreateDate(re.getTimestamp("create_date"));
				result.add(comment);
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
	
	public static List<Rate> findRateByImage(int imageId) {
		List<Rate> result = new ArrayList<Rate>();
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT * FROM DWSJ_RATES WHERE dwsj_image = ?");
			sta.setInt(1, imageId);
			ResultSet re = sta.executeQuery();
			Rate rate = null;
			while (re.next()) {
				rate = new Rate();
				rate.setUserId(re.getInt("dwsj_user"));
				rate.setImageId(re.getInt("dwsj_image"));
				rate.setRate(re.getInt("rate"));
				rate.setCreateDate(re.getTimestamp("create_date"));
				result.add(rate);
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
	
	public static int countCommentByImage(int imageId) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT COUNT(*) AS COUNT_COMMENT FROM DWSJ_COMMENTS WHERE dwsj_image=?");
			sta.setInt(1, imageId);
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				return re.getInt("COUNT_COMMENT");
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
	
	public static int countRateByImage(int imageId) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT COUNT(*) AS COUNT_RATE FROM DWSJ_RATES WHERE dwsj_image=?");
			sta.setInt(1, imageId);
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				return re.getInt("COUNT_RATE");
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
	
	public static boolean checkRated(int userId, int imageId) {
		Connection connection = null;
		PreparedStatement sta = null;
		ConnectMysql mysql = ConnectMysql.getInstance();
		try {
			connection = mysql.getConnection();
			sta = connection.prepareStatement("SELECT COUNT(*) AS COUNT_RATE FROM DWSJ_RATES WHERE dwsj_user=? AND dwsj_image=?");
			sta.setInt(1, userId);
			sta.setInt(2, imageId);
			ResultSet re = sta.executeQuery();
			while (re.next()) {
				if(re.getInt("COUNT_RATE") > 0){
					return true;
				}
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
	
}

