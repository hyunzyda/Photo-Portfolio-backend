package com.kosmo.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private String nickname;
    private String phone;
    private String gender;
    private LocalDate birth;
    private String website;
    private String introduce;
    private String proImage;
    private String role;

    public User() {
    }
    public User(String email, String password) {
    	this.email=email;
    	this.password=password;
    }
    
    public User(String email,String password, String nickname, String phone) {
    	this.email=email;
    	this.password=password;
    	this.nickname=nickname;
    	this.phone=phone;
    }
}
