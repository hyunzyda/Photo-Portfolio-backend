package com.kosmo.project.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.kosmo.project.dao.AuthDAO;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthDAO authDao;
	
    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .cors().and()
            .authorizeRequests()
            .antMatchers("/login", "/signup", "/user/all","/post/**","/like/**","/comment/**").permitAll()
            .antMatchers(HttpMethod.POST, "/**").authenticated()
            .antMatchers(HttpMethod.GET, "/**").authenticated()
            .antMatchers(HttpMethod.PUT, "/**").authenticated()
            .antMatchers(HttpMethod.DELETE, "/**").authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new JwtFilter(authDao, secretKey), UsernamePasswordAuthenticationFilter.class);
    }

}
// addFilterBefore를 이용해 filter를 추가하고 UsernamePasswordAuthenticationFilter클래스가 실행되기 전에 실행되도록 앞에 써준다.
// JwtFilter가 실행되면 클라이언트가 전달한 JWT 토큰을 검증하고, 유효한 토큰이라면 토큰에서 추출한 인증 정보를 
// Spring Security의 SecurityContextHolder에 저장한다. SecurityContextHolder가 가진 Authentication객체에 토큰의 페이로드값이 저장되어있다.
