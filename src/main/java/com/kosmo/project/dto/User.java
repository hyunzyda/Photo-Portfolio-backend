package com.kosmo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private String nickname;
    private String phone;
    private String gender;
    private String introduce;
    private String proImage;
    private String role;
    private int visitCnt;
    private int postCnt;
    private int followerCnt;
    private int followingCnt;

    public User(String email, String password) {
    	this.email=email;
    	this.password=password;
    }
    
    public User(String email,String password, String nickname, String phone, String gender) {
    	this.email=email;
    	this.password=password;
    	this.nickname=nickname;
    	this.phone=phone;
    	this.gender=gender;
    }
}
