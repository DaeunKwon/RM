package com.example.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserVO {
    private String email;
    private String name;
    private String password;

    public UserVO() {
    }

    public UserVO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    
}
