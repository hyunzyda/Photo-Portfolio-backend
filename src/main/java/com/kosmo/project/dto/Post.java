package com.kosmo.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	private int post_id;
	private String email;
	private String content;
	private String image_url;
	private String category;
	private String nickname;
	private int likeCnt;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
}
