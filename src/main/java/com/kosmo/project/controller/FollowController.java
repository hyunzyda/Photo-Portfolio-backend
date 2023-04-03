package com.kosmo.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.FollowDAO;
import com.kosmo.project.dto.User;

@RestController
public class FollowController {

	@Autowired
	private FollowDAO followDao;
	
	// 계정 팔로우
	@PostMapping("/follow/{email}")
	public ResponseEntity<Boolean> followUser(@PathVariable(value="email") String email){
		String myEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = followDao.checkFollow(myEmail,email);
		if(result) {
			boolean status = followDao.deleteFollow(myEmail,email);
			return ResponseEntity.ok().body(status);
		}else {
			boolean status = followDao.createFollow(myEmail,email);
			return ResponseEntity.ok().body(status);
		}
	}
	
	// 내가 팔로우 하는 유저정보
	@GetMapping("/following")
	public  ResponseEntity<List<User>> getAllFollowing(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> follow = followDao.getAllFollowing(email);
		if(follow.size()>0) {
			return ResponseEntity.ok(follow);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 나를 팔로우 하는 유저정보
	@GetMapping("/follower")
	public  ResponseEntity<List<User>> getAllFollower(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> follow = followDao.getAllFollower(email);
		if(follow.size()>0) {
			return ResponseEntity.ok(follow);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
