package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.User;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 모든 사용자 정보 조회
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user where email != 'admin'";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    // 사용자 추가
    public boolean addUser(User user) {
        String sql = "INSERT INTO user (email, password, nickname, phone) VALUES (?, ?, ?, ?)";
        int count = jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getNickname(), user.getPhone());
        return count > 0;
    }

    // 사용자 정보 조회
    @SuppressWarnings("deprecation")
	public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { email }, new UserRowMapper());
    }
    
    // 사용자 프로필사진 변경
    public boolean editProfile(String email,User user) {
    	String sql = "UPDATE user SET profile_image = ?, introduce = ? WHERE email = ?";
    	int count = jdbcTemplate.update(sql,user.getProImage(),user.getIntroduce(), email);
    	return count > 0;
    }

    // 사용자 정보 수정
    public boolean updateUser(User user,String email) {
        String sql = "UPDATE user SET email = ?, password = ?, nickname = ?, phone = ? WHERE email = ?";
        int count = jdbcTemplate.update(sql,user.getEmail(), user.getPassword(), user.getNickname(), user.getPhone(),email);
        return count > 0;
    }

    // 사용자 정보 삭제
    public boolean deleteUser(String email) {
        String sql = "DELETE FROM user WHERE email = ?";
        int count = jdbcTemplate.update(sql, email);
        return count > 0;
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
