package com.kosmo.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.Post;

@Repository
public class PostDAO {
	   final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	   final String DB_URL = "jdbc:mariadb://192.168.0.208:3306/phopo";

	   public Connection open() {
	      Connection conn = null;
	      try {
	         Class.forName(JDBC_DRIVER);
	         conn = DriverManager.getConnection(DB_URL, "kosmo123", "1234");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return conn;
	   }	
	   
	   //모든 게시글 조회
	   public List<Post> getAllPosts(){
		   
		   List<Post> posts = new ArrayList<>();
		   String sql = "SELECT * FROM post";
		   
		   try(Connection conn=open();
			   PreparedStatement pstmt = conn.prepareStatement(sql);
			   ResultSet rs = pstmt.executeQuery();) {
			   while(rs.next()) {
				   Post post = new Post();
				   post.setPost_id(rs.getInt("post_id"));
				   post.setContent(rs.getString("content"));
				   post.setImage_url(rs.getString("image_url"));
				   post.setUser_id(rs.getInt("user_id"));
				   post.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
				   post.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
				   posts.add(post);
			   }
		   } catch(SQLException e) {
			   e.printStackTrace();
		   }
		   return posts;
	   } 
	   
	   // 게시글 추가
	   public boolean addPost(Post post) {
		   String sql = "INSERT INTO post (post_id, content, image_url, user_id, created_at) VALUES(?,?,?,?,?)";
		   
		   try(Connection conn = open();
			   PreparedStatement pstmt = conn.prepareStatement(sql);){
			   pstmt.setInt(1, post.getPost_id());
			   pstmt.setString(2, post.getContent());
			   pstmt.setString(3, post.getImage_url());
			   pstmt.setInt(4, post.getUser_id());
			   pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			
			   
			   int count = pstmt.executeUpdate();
			   return count>0;
		   }catch(SQLException e) {
			   e.printStackTrace();
			   return false;
		   } 
	   }
	   
	   // 게시글 조회
	   public Post getPostById(int id) {
		   
		   String sql = "SELECT * FROM post WHERE post_id = ?";
		   try(Connection conn = open();
			   PreparedStatement pstmt = conn.prepareStatement(sql)){
			   pstmt.setInt(1, id);
			   
			   try(ResultSet rs = pstmt.executeQuery()){
				   if(rs.next()) {
					   Post post = new Post();
					   post.setPost_id(rs.getInt("post_id"));
					   post.setContent(rs.getString("content"));
					   post.setImage_url(rs.getString("image_url"));
					   post.setUser_id(rs.getInt("user_id"));
					   post.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
					   post.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
					   return post;
				   } else {
					   return null;
				   }
			   }
		   }catch(SQLException e) {
			   e.printStackTrace();
			   return null;
		   }
	   }
	   
	   // 게시글 수정
	   public boolean updatePost(int id, Post post) {
		   String sql = "UPDATE post SET content = ?, image_url = ?, modified_at =CURRENT_TIMESTAMP  WHERE post_id = ?";
		   try(Connection conn =open();
			   PreparedStatement pstmt = conn.prepareStatement(sql);){
			   pstmt.setString(1, post.getContent());
			   pstmt.setString(2, post.getImage_url());
		       pstmt.setInt(3, id);
		       int result = pstmt.executeUpdate();
		       return result == 1;
			   			   
		   } catch(SQLException e) {
			   e.printStackTrace();
			   return false;
		   }
	   }
	   
	   // 게시글 삭제
	   public boolean deletePost(int id) {
		   String sql = "DELETE FROM post WHERE post_id = ?";
		   try(Connection conn = open();
			   PreparedStatement pstmt = conn.prepareStatement(sql)){
			   pstmt.setInt(1, id);
			   int result = pstmt.executeUpdate();
			   return result == 1;
		   } catch(SQLException e) {
			   e.printStackTrace();
			   return false;
		   }
	   }
	   
	   
	   
}
