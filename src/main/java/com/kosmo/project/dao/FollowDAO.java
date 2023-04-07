package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.Follow;
import com.kosmo.project.dto.User;

@Repository
public class FollowDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 이전 팔로우 클릭여부 확인
	public boolean checkFollow(String myEmail,String email) {
		String sql = "SELECT COUNT(*) FROM user_follow WHERE follower = ? AND user = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class,myEmail,email);
		return count > 0;
	}

	// 팔로우 제거
	public boolean deleteFollow(String myEmail,String email) {
		String sql1 = "DELETE FROM user_follow WHERE follower = ? AND user = ?";
		jdbcTemplate.update(sql1,myEmail,email);
		String sql2 = "UPDATE user SET follower_count = "
	               + "(SELECT COUNT(*) FROM user_follow WHERE user = ?) "
	               + "WHERE email = ?";
		jdbcTemplate.update(sql2,email,email);
		String sql3 = "UPDATE user SET following_count = "
	               + "(SELECT COUNT(*) FROM user_follow WHERE follower = ?) "
	               + "WHERE email = ?";
		jdbcTemplate.update(sql3,myEmail,myEmail);
		return false;
	}

	// 팔로우 추가
	public boolean createFollow(String myEmail,String email) {
		String sql1 = "INSERT INTO user_follow (follower,user,is_follow) VALUES (?,?,'1')";
		jdbcTemplate.update(sql1,myEmail,email);
		String sql2 = "UPDATE user SET follower_count = "
	               + "(SELECT COUNT(*) FROM user_follow WHERE user = ?) "
	               + "WHERE email = ?";
		jdbcTemplate.update(sql2,email,email);
		String sql3 = "UPDATE user SET following_count = "
	               + "(SELECT COUNT(*) FROM user_follow WHERE follower = ?) "
	               + "WHERE email = ?";
		jdbcTemplate.update(sql3,myEmail,myEmail);
		return true;
	}

	// 팔로잉 정보
	public List<User> getAllFollowing(String email) {
		String sql = "SELECT u.* "
				+ "FROM user u "
				+ "JOIN user_follow f ON u.email = f.user "
				+ "WHERE f.follower = ?";
		return jdbcTemplate.query(sql, new UserRowMapper(), email);
	}

	// 팔로우 정보
	public List<User> getAllFollower(String email) {
		String sql = "SELECT u.* "
				+ "FROM user u "
				+ "JOIN user_follow f ON u.email = f.follower "
				+ "WHERE f.user = ?";
		return jdbcTemplate.query(sql, new UserRowMapper(), email);
	} 
	
    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setPhone(rs.getString("phone"));
            user.setGender(rs.getString("gender"));
            user.setIntroduce(rs.getString("introduce"));
            user.setProImage(rs.getString("profile_image"));
            user.setRole(rs.getString("role"));
            user.setVisitCnt(rs.getInt("visit_count"));
            user.setPostCnt(rs.getInt("post_count"));
            user.setFollowerCnt(rs.getInt("follower_count"));
            user.setFollowingCnt(rs.getInt("following_count"));
            return user;
        }
    }

}
