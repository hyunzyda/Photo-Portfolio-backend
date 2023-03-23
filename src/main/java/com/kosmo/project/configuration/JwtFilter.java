package com.kosmo.project.configuration;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kosmo.project.service.UserService;
import com.kosmo.project.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter{

	private final UserService userService;
	private final String secretKey;
	
	public JwtFilter(UserService userService, String secretKey) {
		this.userService=userService;
		this.secretKey=secretKey;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("authorization:{}",authorization);
		
		// token안보내면 Block
		if(authorization == null || !authorization.startsWith("Bearer ")){
			log.error("authorization 에러");
			filterChain.doFilter(request, response);
			return;
		}
		
		// Token꺼내기
		String token = authorization.split(" ")[1];

		
		// Token Expired여부 확인
		if(JwtUtil.isExpired(token, secretKey)) {
			log.error("Token 만료");
			filterChain.doFilter(request, response);
			return;
		}
		
		
		// Token에서 email 꺼내기
		String email = JwtUtil.getEmailFromToken(token, secretKey);
		log.info("Email:{}",email);
		
		// 권한 부여
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(email,null,List.of(new SimpleGrantedAuthority("USER")));
		// Detail
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}
}
