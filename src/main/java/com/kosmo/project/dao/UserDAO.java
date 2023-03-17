package com.kosmo.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.User;

@Repository
public class UserDAO {
	   final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	   final String DB_URL = "jdbc:mariadb://192.168.0.208:3306/phopo";

	   public Connection open() {
	      Connection conn = null;
	      try {
	         Class.forName(JDBC_DRIVER);
	         conn = DriverManager.getConnection(DB_URL, "kosmo321", "1234");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return conn;
	   }	
	
	    // 모든 사용자 정보 조회
	    public List<User> getAllUsers() {
				
	        List<User> users = new ArrayList<>();
	        String sql = "SELECT * FROM user";	        
	        
	        try(Connection conn=open();	
	        	PreparedStatement pstmt = conn.prepareStatement(sql);	        
	        	ResultSet rs = pstmt.executeQuery();) {
	            while(rs.next()) {
	                User user = new User();	 
	                user.setUserId(rs.getInt("user_id"));
	                user.setEmail(rs.getString("email"));
	                user.setPassword(rs.getString("password"));
	                user.setNickname(rs.getString("nickname"));
	                user.setPhone(rs.getString("phone"));
	                user.setGender(rs.getString("gender"));
	                user.setBirth(rs.getDate("birth").toLocalDate());
	                user.setWebsite(rs.getString("website"));
	                user.setIntroduce(rs.getString("introduce"));
	                user.setProImage(rs.getString("profile_image"));
	                user.setRole(rs.getString("role"));
	                                
	                users.add(user);
	            }
	        } catch(SQLException e) {
	            e.printStackTrace();
	        }
	        return users;
	    }
	    
	    
	    // 사용자 추가
	    public boolean addUser(User user) {		
	        String sql = "INSERT INTO user (email,password,nickname,phone) VALUES (?,?,?,?)";	  
	        try(Connection conn=open();
	        	PreparedStatement pstmt = conn.prepareStatement(sql);) {
	        	pstmt.setString(1, user.getEmail());
	        	pstmt.setString(2, user.getPassword());
	        	pstmt.setString(3, user.getNickname());
	        	pstmt.setString(4, user.getPhone());

	            int count = pstmt.executeUpdate();
	            return count > 0;
	        } catch(SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    // 사용자 정보 조회
	    public User getUserById(int id) {
			
	        String sql = "SELECT * FROM user WHERE user_id=?";
	        try(Connection conn=open();
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, id);

	            try(ResultSet rs = pstmt.executeQuery()) {
	                if(rs.next()) {
	                    User user = new User();
		                user.setUserId(rs.getInt("user_id"));
		                user.setEmail(rs.getString("email"));
		                user.setPassword(rs.getString("password"));
		                user.setNickname(rs.getString("nickname"));
		                user.setPhone(rs.getString("phone"));
		                user.setGender(rs.getString("gender"));
		                user.setBirth(rs.getDate("birth").toLocalDate());
		                user.setWebsite(rs.getString("website"));
		                user.setIntroduce(rs.getString("introduce"));
		                user.setProImage(rs.getString("profile_image"));
		                user.setRole(rs.getString("role"));
	                    return user;
	                } else {
	                    return null;
	                }
	            }
	        } catch(SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    // 사용자 정보 수정
	    public boolean updateUser(int id, User user) {
	        String sql = "UPDATE user SET email = ?, password = ?, nickname = ?, phone = ? WHERE user_id = ?";
	        try(Connection conn=open();
	        	PreparedStatement pstmt = conn.prepareStatement(sql);) {
	        	pstmt.setString(1, user.getEmail());
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getNickname());
	            pstmt.setString(4, user.getPhone());
	            pstmt.setInt(5, id);

	            int count = pstmt.executeUpdate();
	            return count > 0;
	        } catch(SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    // 사용자 정보 삭제
	    public boolean deleteUser(int id) {
	        String sql = "DELETE FROM user WHERE user_id = ?";
	        try(Connection conn=open();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            int count = pstmt.executeUpdate();
	            return count > 0;
	        } catch(SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

}
