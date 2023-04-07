package com.kosmo.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.NoticeDAO;
import com.kosmo.project.dto.Notice;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeDAO noticeDao;
	
	// 모든 공지사항 조회
	@GetMapping("")
	public ResponseEntity<List<Notice>> getAllNotices(){
		List<Notice> notices = noticeDao.getAllNotices();
		if(notices.size()>0) {
			return ResponseEntity.ok(notices);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 공지사항 추가
	@PostMapping("/add")
	public ResponseEntity<Void> addNotice(@Valid @RequestBody Notice notice){
		boolean result = noticeDao.addNotice(notice);
		if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// 공지사항 수정
	@PutMapping("/{noticeId}")
	public ResponseEntity<Void> updateNotice(@PathVariable(value="noticeId") int noticeId, @Valid @RequestBody Notice notice){
		boolean result = noticeDao.updateNotice(noticeId, notice);	
		if(result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 공지사항 삭제
	@DeleteMapping("/{noticeId}")
	public ResponseEntity<Void> deleteNotice(@PathVariable(value="noticeId") int noticeId){
		boolean result = noticeDao.deleteNotice(noticeId);
		if(result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
