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

import com.kosmo.project.dto.Comment;

@Repository
public class CommentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

    // 게시물별 댓글 조회
    public List<Comment> getCommentById(int id) {
    	String sql = "SELECT * FROM comment WHERE post_id = ?";
        List<Comment> comments = jdbcTemplate.query(sql, new Object[]{id}, new CommentRowMapper());
        return comments;
    }
    
    
    // 댓글 추가
//    public boolean addComment(String email,int post_id,Comment comment) {
//        String sql1 = "INSERT INTO comment (email,post_id,content,created_at,nickname) VALUES (?,?,?,?,?)";
//        String sql2 = "SELECT nickname FROM user WHERE email = ?";
//        String nickname = jdbcTemplate.queryForObject(sql2, new Object[] { email }, String.class);
//        int result =  jdbcTemplate.update(sql1,email, post_id, comment.getContent(), LocalDateTime.now(), nickname);
//        return result > 0;  			
//    }
    
    public Comment addComment(String email,int post_id,Comment comment) {
        String sql1 = "INSERT INTO comment (email,post_id,content,created_at,nickname) VALUES (?,?,?,?,?)";
        String sql2 = "SELECT nickname FROM user WHERE email = ?";
        String nickname = jdbcTemplate.queryForObject(sql2, new Object[] { email }, String.class);
        jdbcTemplate.update(sql1,email, post_id, comment.getContent(), LocalDateTime.now(), nickname);
        
        String sql3 = "SELECT * FROM comment WHERE email = ? AND post_id = ? AND content = ?";
        Comment comments = jdbcTemplate.queryForObject(sql3, new Object[] { email, post_id, comment.getContent() },new CommentRowMapper());
        return comments;  			
    }
    
    // 댓글 삭제
	public boolean deleteCommentById(int id) {
        String sql = "DELETE FROM comment WHERE comment_id=?";
        int result = jdbcTemplate.update(sql, id);
        return (result > 0);
	}  
	
	private class CommentRowMapper implements RowMapper<Comment>{
	   @Override
	   public Comment mapRow(ResultSet rs, int rowNum) throws SQLException{
		   Comment cm = new Comment();
		   cm.setComment_id(rs.getInt("comment_id"));
		   cm.setPost_id(rs.getInt("post_id"));
		   cm.setEmail(rs.getString("email"));
		   cm.setContent(rs.getString("content"));
		   cm.setNickname(rs.getString("nickname"));
		   cm.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
		   cm.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
		   return cm;
		   
	   }
	}

}
