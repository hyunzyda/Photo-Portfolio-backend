package com.kosmo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostLike {
	private int postLikeId;
	private String email;
	private int postId;
	private int isLiked;
}
