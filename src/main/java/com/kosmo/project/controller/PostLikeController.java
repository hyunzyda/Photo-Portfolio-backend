package com.kosmo.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.LikeDAO;
import com.kosmo.project.dto.PostLike;

@RestController
@RequestMapping("/like")
public class PostLikeController {

	@Autowired
	private LikeDAO LikeDao;
	// 좋아요 현황 조회
	@GetMapping("")
	public ResponseEntity<List<PostLike>> getAllPosts(){
		List<PostLike> posts = LikeDao.getAllLikes();
		if(posts.size() > 0 ) {
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 좋아요 현황 조회
	@GetMapping("/{postId}")
	public ResponseEntity<List<PostLike>> getPostsByEmail(@PathVariable(value="postId") int postId){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		List<PostLike> posts = LikeDao.getLikesByEmail(email,postId);
		if(posts == null ) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(posts);
		}
	}
}
