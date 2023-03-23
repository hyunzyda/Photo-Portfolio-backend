package com.kosmo.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.Comment;
import com.kosmo.project.dto.Post;

@Repository
public class CommentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

    // 게시물별 댓글 조회
    public Comment getCommentById(int id) {
		
        String sql = "SELECT * FROM comment WHERE post_id=?";

            return null;
    }
    
    
    //댓글 추가
    public boolean addComment(Comment comment) {
    	String sql = "INSERT INTO comment (email,post_id,content,created_at) VALUES (?,?,?,?)";
    	int result =  jdbcTemplate.update(sql, comment.getPost_id(), comment.getContent(), comment.getCreated_at());
    	return result > 0;
    			
    }
    
	private class CommentRowMapper implements RowMapper<Comment>{
	   @Override
	   public Comment mapRow(ResultSet rs, int rowNum) throws SQLException{
		   Comment cm = new Comment();
		   cm.setComment_id(rs.getInt("comment_id"));
		   cm.setPost_id(rs.getInt("post_id"));
		   cm.setEmail(rs.getString("email"));
		   cm.setContent(rs.getString("content"));
		   cm.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
		   cm.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
		   return cm;
		   
	   }
	}   
}
