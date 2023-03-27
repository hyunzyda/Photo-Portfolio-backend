package com.kosmo.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.LikeDAO;
import com.kosmo.project.dto.PostLike;

@RestController
public class PostLikeController {

	@Autowired
	private LikeDAO LikeDao;
	// 좋아요 현황 조회
	@GetMapping("/like")
	public ResponseEntity<List<PostLike>> getAllPosts(){
		List<PostLike> posts = LikeDao.getAllLikes();
		if(posts.size() > 0 ) {
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
