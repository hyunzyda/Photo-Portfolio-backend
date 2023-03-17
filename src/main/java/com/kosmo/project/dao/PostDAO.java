package com.kosmo.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.PostDTO;

@Repository
public class PostDAO {
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
	   
	   //모든 게시글 조회
	   public List<PostDTO> getAllPosts(){
		   
		   List<PostDTO> posts = new ArrayList<>();
		   String sql = "SELECT * FROM post";
		   
		   try(Connection conn=open();
			   PreparedStatement pstmt = conn.prepareStatement(sql);
			   ResultSet rs = pstmt.executeQuery();) {
			   while(rs.next()) {
				   PostDTO post = new PostDTO();
				   post.setPost_id(rs.getInt("post_id"));
				   post.setContent(rs.getString("content"));
				   post.setImage_url(rs.getString("image_url"));
				   post.setUser_id(rs.getInt("user_id"));
				   post.setCreated_at(rs.getDate("created_at").toLocalDate());
				   posts.add(post);
			   }
		   } catch(SQLException e) {
			   e.printStackTrace();
		   }
		   return posts;
	   } 
	   
	   // 게시글 추가
	   public boolean addPost(PostDTO post) {
		   String sql = "INSERT INTO post (post_id, content, image_url, user_id, created_at) VALUES(?,?,?,?,?)";
		   
		   try(Connection conn = open();
			   PreparedStatement pstmt = conn.prepareStatement(sql);){
			   pstmt.setInt(1, post.getPost_id());
			   pstmt.setString(2, post.getContent());
			   pstmt.setString(3, post.getImage_url());
			   pstmt.setInt(4, post.getUser_id());
			   pstmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			
			   
			   int count = pstmt.executeUpdate();
			   return count>0;
		   }catch(SQLException e) {
			   e.printStackTrace();
			   return false;
		   } 
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
