package com.kosmo.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Post {

	private int post_id;
	private String content;
	private String image_url;
	private int user_id;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
	
	public Post() {}
}
