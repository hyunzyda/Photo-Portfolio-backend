package com.kosmo.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PostDTO {

	private int post_id;
	private String content;
	private String image_url;
	private int user_id;
	private LocalDate created_at;
	private LocalDate modified_at;
	
	public PostDTO() {}
}
