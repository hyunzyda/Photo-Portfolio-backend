package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.PostLike;

@Repository
public class LikeDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
   // 좋아요 현황 조회
   public List<PostLike> getAllLikes(){
	   String sql = "SELECT * FROM post_like";
	   return jdbcTemplate.query(sql, new PostLikeRowMapper());
   }
 
   @SuppressWarnings("deprecation")
   public List<PostLike> getLikesByEmail(String email, int postId) {
	   String sql = "SELECT * FROM post_like WHERE email = ? AND post_id = ?";
	   List<PostLike> post = jdbcTemplate.query(sql, new Object[]{email, postId}, new PostLikeRowMapper());
	   return post;
   }
   
   private class PostLikeRowMapper implements RowMapper<PostLike>{
	   @Override
	   public PostLike mapRow(ResultSet rs, int rowNum) throws SQLException{
		   PostLike pl = new PostLike();
		   pl.setPostLikeId(rs.getInt("post_like_id"));
		   pl.setEmail(rs.getString("email"));
		   pl.setPostId(rs.getInt("post_id"));
		   pl.setPostLikeId(rs.getInt("is_liked"));
		   return pl;
		   
	   }
   }
}
