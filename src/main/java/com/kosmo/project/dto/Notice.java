package com.kosmo.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Notice {

	private int notice_id;
	private String title;
	private String content;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
	public Notice() {}
}
