package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.Post;

@Repository
public class PostDAO {
		@Autowired
	    private JdbcTemplate jdbcTemplate;
		
	   // 모든 게시글 조회
	   public List<Post> getAllPosts(){
		   String sql = "SELECT * FROM post";
		   return jdbcTemplate.query(sql, new PostRowMapper());
	   }
	   
	   // 게시글 추가
	   public boolean addPost(Post post) {
		   String sql = "INSERT INTO post (email, content, image_url, user_id, created_at) VALUES(?,?,?,?,?)";
		   int count = jdbcTemplate.update(sql, post.getEmail(), post.getContent(), post.getImage_url(), post.getUser_id(), post.getCreated_at());
		   return count > 0;
	   }
	   
	   // 게시글 조회
	   @SuppressWarnings("deprecation")
	   public Post getPostByEmail(String email) {
		    String sql = "SELECT * FROM post WHERE email = ?";
		    return jdbcTemplate.queryForObject(sql, new Object[] { email }, new PostRowMapper());
		}
	   
	   // 게시글 수정
	   public boolean updatePost(String email, Post post) {
		   String sql = "UPDATE post SET content = ?, image_url = ?, modified_at =CURRENT_TIMESTAMP  WHERE email = ?";
		   int count = jdbcTemplate.update(sql, post.getContent(), post.getImage_url(), email);
		   return count > 0;
	   }
	   
	   // 게시글 삭제
	   public boolean deletePost(String email) {
		   String sql = "DELETE FROM post WHERE email = ?";
		   int count = jdbcTemplate.update(sql, email);
		   return count > 0;
	   }
	   
	   private class PostRowMapper implements RowMapper<Post>{
		   @Override
		   public Post mapRow(ResultSet rs, int rowNum) throws SQLException{
			   Post post = new Post();
			   post.setEmail(rs.getString("email"));
			   post.setContent(rs.getString("content"));
			   post.setImage_url(rs.getString("image_url"));
			   post.setUser_id(rs.getInt("user_id"));
			   post.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
			   post.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
			   return post;
			   
		   }
	   }
	    
	   
}
