package com.kosmo.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Comment> getComment(@PathVariable(value="id") int id){
		Comment comment = commentDao.getCommentById(id);
		if(comment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(comment);
	}
	
	// 댓글 추가
	@PostMapping("/add")
	public ResponseEntity<Comment> addComment(@Valid @RequestBody Comment comment){
		boolean result = commentDao.addComment(comment);
		if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
