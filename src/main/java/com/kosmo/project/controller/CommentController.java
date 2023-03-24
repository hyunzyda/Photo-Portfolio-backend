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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.CommentDAO;
import com.kosmo.project.dto.Comment;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentDAO commentDao;
	
	// 게시물별 댓글 조회
	@GetMapping("/{commentId}")
	public ResponseEntity<List<Comment>> getComment(@PathVariable(value="commentId") int id){
		List<Comment> comment = commentDao.getCommentById(id);
		if(comment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(comment);
	}
	
	// 댓글 추가
	@PostMapping("/{postId}")
	public ResponseEntity<Comment> addComment(@PathVariable(value="postId") int post_id ,@Valid @RequestBody Comment comment){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean result = commentDao.addComment(email,post_id,comment);
		if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	// 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value="commentId") int id) {
        boolean result = commentDao.deleteCommentById(id);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }	
}
