package com.kosmo.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

	private int notice_id;
	private String title;
	private String content;
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
}
