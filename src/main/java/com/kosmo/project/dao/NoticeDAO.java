package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.Notice;

@Repository
public class NoticeDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 모든 공지사항 조회
	public List<Notice> getAllNotices(){
		String sql = "SELECT * FROM notice";
		return jdbcTemplate.query(sql, new NoticeRowMapper());
	}
	
	// 공지사항 추가
	public boolean addNotice(Notice notice) {
		String sql = "INSERT INTO notice (title,content,created_at) VALUES(?,?,?)";
		int count = jdbcTemplate.update(sql,notice.getTitle(),notice.getContent(),LocalDateTime.now());
		return count > 0;
	}
	
    // 공지사항 수정
    public boolean updateNotice(int noticeId, Notice notice) {
	    String sql = "UPDATE notice SET content = ?, modified_at =CURRENT_TIMESTAMP  WHERE notice_id = ?";
	    int count = jdbcTemplate.update(sql, notice.getContent(), noticeId);
	    return count > 0;
    }
   
    // 공지사항 삭제
    public boolean deleteNotice(int noticeId) {
	    String sql = "DELETE FROM notice WHERE notice_id = ?";
	    int count = jdbcTemplate.update(sql, noticeId);
	    return count > 0;
    }
	
	private class NoticeRowMapper implements RowMapper<Notice>{
		@Override
		public Notice mapRow(ResultSet rs, int rowNum) throws SQLException{
			Notice notice = new Notice();
			notice.setNotice_id(rs.getInt("notice_id"));
			notice.setTitle(rs.getString("title"));
			notice.setContent(rs.getString("content"));
			notice.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
			notice.setModified_at(rs.getTimestamp("modified_at").toLocalDateTime());
			return notice;
		}
	}
}
