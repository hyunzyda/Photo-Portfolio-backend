package com.kosmo.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosmo.project.dao.PostDAO;
import com.kosmo.project.dto.Post;
import com.kosmo.project.service.S3Service;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostDAO postDao;
	@Autowired
	private S3Service s3Service;

	
	// 모든 게시글 조회
	@GetMapping("/all")
	public ResponseEntity<List<Post>> getAllPosts(){
		List<Post> posts = postDao.getAllPosts();
		if(posts.size() > 0 ) {
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 모든 게시글 조회(로그인된 사용자 제외)
	@GetMapping("")	
	public ResponseEntity<List<Post>> getAllPostsMe(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Post> posts = postDao.getAllPostsMe(email);
		if(posts.size() > 0 ) {
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//게시글 추가
	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> addPost(@Valid @ModelAttribute Post post,@RequestParam(value="file", required = false) MultipartFile file) {
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    String fileUrl = null;
	    if (file != null) {
	        fileUrl = s3Service.saveFile(file);
	    }
	    post.setImage_url(fileUrl);
	    try {
	        postDao.addPost(email, post);
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	// 사용자별 게시글 조회
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Post>> getPostEmail(@PathVariable(value="email") String email){
		List<Post> post = postDao.getPostByEmail(email);
		// 방문자수 증가
		postDao.saveUserVisit(email);
		if(post == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(post);
	}
	
	// 게시글 수정
	@PutMapping("/{postId}")
	public ResponseEntity<Void> updatePost(@PathVariable(value="postId") int postId, @Valid @RequestBody Post post){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = postDao.updatePost(postId, email, post);
		if(result) {
			return ResponseEntity.noContent().build();
		 } else {
			return ResponseEntity.notFound().build();
		 }
	}
	
	// 게시글 삭제
	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value="postId") int postId){
		boolean result = postDao.deletePost(postId);
		if(result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 게시글별 좋아요수 증가
	@PostMapping("/like/{postId}")
	public ResponseEntity<Boolean> likePost(@PathVariable(value="postId") int postId){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = postDao.checkLike(postId,email);
		if(result) {
			boolean status = postDao.decreaseLike(postId, email);
			return ResponseEntity.ok().body(status);
		}else {
			boolean status = postDao.increaseLike(postId, email);
			return ResponseEntity.ok().body(status);
		}
	}	
}
