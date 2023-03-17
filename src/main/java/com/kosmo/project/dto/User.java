package com.kosmo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class User {

    private int id;
    private String userid;
    private String userps;
    private String username;

    public User() {
    }
}
