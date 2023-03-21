package com.kosmo.project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kosmo.project.util.JwtUtil;

@Service
public class UserService {
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Long expiredMs = 1000 * 60 * 60l;
	public String login(String email,String password) {
		return JwtUtil.createJwt(email, secretKey, expiredMs);
	}
}
