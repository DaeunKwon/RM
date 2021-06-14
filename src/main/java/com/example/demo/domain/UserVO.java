package com.example.demo.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class UserVO {
    private String email;
    private String name;
    private String password;
    private String rmv_YN;
    private List<String> roles;
    private String authority;

    public UserVO() {
    }

    public UserVO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserVO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Builder
    public UserVO(String email, String name, String password, String rmv_YN, List<String> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.rmv_YN = rmv_YN;
        this.roles = roles;
    }

}
