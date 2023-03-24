package com.kosmo.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.PostDAO;
import com.kosmo.project.dto.Post;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostDAO postDao;

	//모든 게시글 조회
	@GetMapping("")
	public ResponseEntity<List<Post>> getAllPosts(){
		List<Post> posts = postDao.getAllPosts();
		if(posts.size() > 0 ) {
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//게시글 추가
	@PostMapping("/add")
	public ResponseEntity<Void> addPost(@Valid @RequestBody Post post){
		// 이런식으로 토큰payload로 정의되어있는 email을 꺼내서 쓸수있다
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		boolean result = postDao.addPost(email,post);
		if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// 사용자별 게시글 조회
	@GetMapping("/{email}")
	public ResponseEntity<List<Post>> getPost(@PathVariable(value="email") String email){
		List<Post> post = postDao.getPostByEmail(email);
		if(post == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(post);
	}
	
	// 게시글 수정
	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePost(@PathVariable(value="id") int postId, @Valid @RequestBody Post post){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = postDao.updatePost(postId, email, post);
		if(result) {
			return ResponseEntity.noContent().build();
		 } else {
			return ResponseEntity.notFound().build();
		 }
	}
	
	// 게시글 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value="id") int postId){
		boolean result = postDao.deletePost(postId);
		if(result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 게시글별 좋아요수 증가
	@PostMapping("/like/{id}")
	public ResponseEntity<Void> likePost(@PathVariable(value="id") int postId){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = postDao.checkLike(postId,email);
		if(result) {
			postDao.increaseLike(postId, email);
			return ResponseEntity.ok().build();
		}else {
			postDao.decreaseLike(postId, email);
			return ResponseEntity.ok().build();
		}
	}	
}
