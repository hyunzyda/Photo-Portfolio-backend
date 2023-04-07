package com.kosmo.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.User;
//import com.kosmo.project.service.UserService;
import com.kosmo.project.util.JwtUtil;

@Repository
public class AuthDAO {
	@Value("${jwt.secret}")
	private String secretKey;
	@Autowired
    private JdbcTemplate jdbcTemplate;
    
	private Long expiredMs = 1000 * 60 * 60 * 24 * 30l;
    
    public AuthDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }    

    public int createUser(User user) {
        String sql = "INSERT INTO user(email, password, nickname, phone, gender) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getNickname(), user.getPhone(), user.getGender());
    }
    
    public boolean existsByUsername(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }
	
	@SuppressWarnings("deprecation")
	public User getUserByEmail(String email) {
	    String sql = "SELECT * FROM user WHERE email = ?";
	    try {
	        User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
	        return user;
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}
    
	public String createToken(String email) {
		return JwtUtil.createJwt(email, secretKey, expiredMs);
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
            return user;
        }
    }
}
