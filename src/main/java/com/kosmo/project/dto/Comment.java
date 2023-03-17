package com.kosmo.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Comment {
	private int comment_id;
	private int post_id;
	private int	user_id;
	private String content;
	private LocalDate created_at;
	private LocalDate modified_at;
	
	public Comment() {	
	}
}
