package com.kosmo.project.dao;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
    
	private Long expiredMs = 1000 * 60 * 60l;
    
    public AuthDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }    

    public int createUser(User user) {
        String sql = "INSERT INTO user(email, password, nickname, phone) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getNickname(), user.getPhone());
    }

    public boolean existsByUsername(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }

    public Optional<User> findByUsername(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    public int loginUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email, password);
        return count;
    }
	
	public String createToken(String email) {
		return JwtUtil.createJwt(email, secretKey, expiredMs);
	}
}
