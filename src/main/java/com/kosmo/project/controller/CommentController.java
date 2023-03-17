package com.kosmo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
