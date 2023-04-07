package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		
		// 모든 게시글 조회(로그인된 사용자 제외)
		public List<Post> getAllPostsMe(String email){
		    String sql = "SELECT * FROM post where email <> ?";
		    return jdbcTemplate.query(sql, new Object[] {email}, new PostRowMapper());
		}
	   
	   // 게시글 추가
	   public void addPost(String email,Post post) {
		    String sql1 = "INSERT INTO post (email, image_url, content, category, created_at, nickname) VALUES(?,?,?,?,?,?)";
		    String sql2 = "SELECT nickname FROM user WHERE email = ?";
		    String nickname = jdbcTemplate.queryForObject(sql2, new Object[]{email}, String.class);
		    jdbcTemplate.update(sql1, email, post.getImage_url(), post.getContent(), post.getCategory(), LocalDateTime.now(), nickname);
		    String sql3 = "UPDATE user SET post_count = (SELECT COUNT(*) FROM post WHERE email = ?) WHERE email = ?";
		    jdbcTemplate.update(sql3, email, email);
		    post.setNickname(nickname);
	   }
	   
	   // 게시글 추가
//	   public Post addPost(String email,Post post) {
//		    String sql1 = "INSERT INTO post (email, image_url, content, category, created_at, nickname) VALUES(?,?,?,?,?,?)";
//		    String sql2 = "SELECT nickname FROM user WHERE email = ?";
//		    String nickname = jdbcTemplate.queryForObject(sql2, new Object[]{email}, String.class);
//		    jdbcTemplate.update(sql1, email, post.getImage_url(), post.getContent(), post.getCategory(), LocalDateTime.now(), nickname);
//		    String sql3 = "UPDATE user SET post_count = (SELECT COUNT(*) FROM post WHERE email = ?) WHERE email = ?";
//		    jdbcTemplate.update(sql3, email, email);
//		    post.setNickname(nickname);
//		    
//		    String sql4 = "SELECT * FROM post WHERE email = ? AND content = ?";
//		    Post posts = jdbcTemplate.queryForObject(sql4, new Object[] { email, post.getContent() }, new PostRowMapper());
//	        return posts;  
//	   }
	   
	   // 이메일별 게시글 조회
	   @SuppressWarnings("deprecation")
	   public List<Post> getPostByEmail(String email) {
		    String sql = "SELECT * FROM post WHERE email = ?";
		    List<Post> post = jdbcTemplate.query(sql, new Object[] {email}, new PostRowMapper());
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
	   
	   // 이전에 게시글 좋아요 클릭여부 확인
	   public boolean checkLike(int postId, String email) {
		   String sql = "SELECT COUNT(*) FROM post_like WHERE post_id = ? AND email = ?";
		   int count = jdbcTemplate.queryForObject(sql, Integer.class,postId,email);
		   return count > 0;
	   }
	   
	   // 게시글 좋아요수 감소	   
	   public boolean decreaseLike(int postId, String email) {
		   String sql1 = "DELETE FROM post_like WHERE post_id = ? AND email = ?";
		   jdbcTemplate.update(sql1, postId, email);
		   String sql2 = "UPDATE post SET like_count = "
	               + "(SELECT COUNT(*) FROM post_like WHERE post_id = ?) "
	               + "WHERE post_id = ?";
		   jdbcTemplate.update(sql2, postId, postId);
		   return false;
	   }
			
	   // 게시글 좋아요수 증가
	   public boolean increaseLike(int postId, String email) {
		   String sql1 = "INSERT INTO post_like (post_id, email,is_liked) VALUES (?, ?,'1')";
		   jdbcTemplate.update(sql1, postId, email);
		   String sql2 = "UPDATE post SET like_count = "
	               + "(SELECT COUNT(*) FROM post_like WHERE post_id = ?) "
	               + "WHERE post_id = ?";
		   jdbcTemplate.update(sql2, postId, postId);
		   return true;
	   }
		
	   // 사용자 방문 기록 저장
	   public void saveUserVisit(String email) {
		   String sql = "UPDATE user SET visit_count = visit_count + 1 WHERE email= ?";
		   jdbcTemplate.update(sql, email);
	   }
	
	   private class PostRowMapper implements RowMapper<Post>{
		   @Override
		   public Post mapRow(ResultSet rs, int rowNum) throws SQLException{
			   Post post = new Post();
			   post.setPost_id(rs.getInt("post_id"));
			   post.setEmail(rs.getString("email"));
			   post.setContent(rs.getString("content"));
			   post.setImage_url(rs.getString("image_url"));
			   post.setCategory(rs.getString("category"));
			   post.setNickname(rs.getString("nickname"));
			   post.setLikeCnt(rs.getInt("like_count"));
			   post.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
			   post.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
			   return post;
			   
		   }
	   }
}
