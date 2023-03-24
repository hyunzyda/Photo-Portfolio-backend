package com.kosmo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PostLike {
	private int userLikeId;
	private String email;
	private int postId;
	
	public PostLike() {
		
	}
}
