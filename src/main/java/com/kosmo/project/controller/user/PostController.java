package com.kosmo.project.controller.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.PostDAO;
import com.kosmo.project.dto.PostDTO;

@RestController
public class PostController {
	
	@Autowired
	private PostDAO postDao;

	//모든 게시글 조회
	@GetMapping("/post")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> posts = postDao.getAllPosts();
		if(posts.size() > 0 ) {
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//게시글 추가
	@PostMapping("/post/add")
	public ResponseEntity<Void> addPost(@Valid @RequestBody PostDTO post){
		boolean result = postDao.addPost(post);
		if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
