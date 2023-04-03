package com.kosmo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Follow {
	private int followId;
	private String user;
	private String follower;
	
	public Follow() {
		
	}
}
