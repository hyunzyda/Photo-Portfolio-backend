package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
	   public boolean addPost(String email,Post post) {
		   String sql = "INSERT INTO post (email, content, image_url, created_at) VALUES(?,?,?,?)";
		   int count = jdbcTemplate.update(sql, email, post.getContent(), post.getImage_url(), LocalDateTime.now());
		   return count > 0;
	   }
	   
	   // 게시글 조회
	   @SuppressWarnings("deprecation")
	   public List<Post> getPostByEmail(String email) {
		    String sql = "SELECT * FROM post WHERE email = ?";
		    List<Post> post = jdbcTemplate.query(sql, new Object[] { email }, new PostRowMapper());
		    return post;
		}
	   
	   // 게시글 수정
	   public boolean updatePost(int postId, String email, Post post) {
		   String sql = "UPDATE post SET content = ?, image_url = ?, modified_at =CURRENT_TIMESTAMP  WHERE post_id = ? and email = ? ";
		   int count = jdbcTemplate.update(sql, post.getContent(), post.getImage_url(), postId, email);
		   return count > 0;
	   }
	   
	   // 게시글 삭제
	   public boolean deletePost(int postId) {
		   String sql = "DELETE FROM post WHERE post_id = ?";
		   int count = jdbcTemplate.update(sql, postId);
		   return count > 0;
	   }
	   
	   // 게시글 좋아요수 관리
	   
	   private class PostRowMapper implements RowMapper<Post>{
		   @Override
		   public Post mapRow(ResultSet rs, int rowNum) throws SQLException{
			   Post post = new Post();
			   post.setPost_id(rs.getInt("post_id"));
			   post.setEmail(rs.getString("email"));
			   post.setContent(rs.getString("content"));
			   post.setImage_url(rs.getString("image_url"));
			   post.setLikeCnt(rs.getInt("like_count"));
			   post.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
			   post.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
			   return post;
			   
		   }
	   }
	    
	   
}
