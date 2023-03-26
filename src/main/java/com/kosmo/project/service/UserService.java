//<<<<<<< HEAD
//package com.kosmo.project.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.kosmo.project.util.JwtUtil;
//
//@Service
//public class UserService {
//	@Value("${jwt.secret}")
//	private String secretKey;
//	
//	private Long expiredMs = 1000 * 60 * 60 * 24l;
//	public String login(String email,String password, int userid) {
//		return JwtUtil.createJwt(email, secretKey, expiredMs);
//	}
//}
//=======
////package com.kosmo.project.service;
////
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.stereotype.Service;
////
////import com.kosmo.project.util.JwtUtil;
////
////@Service
////public class UserService {
////	@Value("${jwt.secret}")
////	private String secretKey;	
////	private Long expiredMs = 1000 * 60 * 60 *24l;
////	
////	public String login(String email,String password, long userid) {
////		return JwtUtil.createJwt(email, secretKey, expiredMs);
////	}
////}
//>>>>>>> branch 'master' of https://github.com/Jun02da/final_project_backend.git
