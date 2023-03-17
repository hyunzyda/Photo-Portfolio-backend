package com.kosmo.project.dto;

public class User {

    private int id;
    private String userid;
    private String userps;
    private String username;

    public User() {
    }

    public User(int id, String userid, String userps, String username) {
        this.id = id;
        this.userid = userid;
        this.userps = userps;
        this.username = username;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserps() {
		return userps;
	}

	public void setUserps(String userps) {
		this.userps = userps;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
