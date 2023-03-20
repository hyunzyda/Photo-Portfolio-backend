package com.kosmo.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.Comment;

@Repository
public class CommentDAO {
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

	    // 게시물별 댓글 조회
	    public Comment getCommentById(int id) {
			
	        String sql = "SELECT * FROM comment WHERE post_id=?";
	        
	        try(Connection conn=open();
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, id);

	            try(ResultSet rs = pstmt.executeQuery()) {
	                if(rs.next()) {
	                    Comment comment = new Comment();
	                    comment.setComment_id(rs.getInt("comment_id"));
	                    comment.setContent(rs.getString("content"));
	                    comment.setPost_id(rs.getInt("post_id"));
	                    comment.setUser_id(rs.getInt("user_id"));
	                    comment.setCreated_at(rs.getDate("created_at").toLocalDate());
	                    comment.setModified_at(rs.getDate("modified_at").toLocalDate());

	                    return comment;
	                } else {
	                    return null;
	                }
	            }
	        } catch(SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }	   
}
