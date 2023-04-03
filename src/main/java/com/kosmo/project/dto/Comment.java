package com.kosmo.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Comment {
	private int comment_id;
	private int post_id;
	private String email;
	private String content;
	private String nickname;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
	
	public Comment() {	
	}
}