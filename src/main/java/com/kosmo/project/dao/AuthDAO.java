package com.kosmo.project.dao;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.project.dto.User;

@Repository
public class AuthDAO {
    private JdbcTemplate jdbcTemplate;
    
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
}
