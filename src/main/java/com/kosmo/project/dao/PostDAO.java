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
		   String sql = "INSERT INTO post (post_id, content, image_url, user_id, created_at) VALUES(?,?,?,?,?)";
		   int result = jdbcTemplate.update(sql, post.getPost_id(), post.getContent(), post.getImage_url(), post.getUser_id(), post.getCreated_at());
		   return result==1;
	   }
	   
	   // 게시글 조회
	   @SuppressWarnings("deprecation")
	   public Post getPostById(int id) {
		    String sql = "SELECT * FROM post WHERE post_id = ?";
		    return jdbcTemplate.queryForObject(sql, new Object[] { id }, new PostRowMapper());
		}
	   
	   // 게시글 수정
	   public boolean updatePost(int id, Post post) {
		   String sql = "UPDATE post SET content = ?, image_url = ?, modified_at =CURRENT_TIMESTAMP  WHERE post_id = ?";
		   int result = jdbcTemplate.update(sql, post.getContent(), post.getImage_url(), id);
		   return result == 1;
	   }
	   
	   // 게시글 삭제
	   public boolean deletePost(int id) {
		   String sql = "DELETE FROM post WHERE post_id = ?";
		   int result = jdbcTemplate.update(sql, id);
		   return result == 1;
	   }
	   
	   private class PostRowMapper implements RowMapper<Post>{
		   @Override
		   public Post mapRow(ResultSet rs, int rowNum) throws SQLException{
			   Post post = new Post();
			   post.setPost_id(rs.getInt("post_id"));
			   post.setContent(rs.getString("content"));
			   post.setImage_url(rs.getString("image_url"));
			   post.setUser_id(rs.getInt("user_id"));
			   post.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
			   post.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
			   return post;
			   
		   }
	   }
	    
	   
}
