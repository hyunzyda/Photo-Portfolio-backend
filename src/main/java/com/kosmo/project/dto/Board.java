package com.kosmo.project.dto;

import java.sql.Date;

public class Board {
	private int bid;
	private String title;
	private String bdate;
	private String content;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return bdate;
	}
	public void setDate(String bdate) {
		this.bdate = bdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
