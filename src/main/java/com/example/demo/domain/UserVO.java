package com.example.demo.domain;

import lombok.Data;

@Data
public class UserVO {
    private String email;
    private String name;
    private String password;
    private String rmv_YN;

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
