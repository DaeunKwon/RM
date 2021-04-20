package com.example.demo.service;

import com.example.demo.domain.UserVO;

public interface UserService {
    public void join(UserVO uvo);

    public void modify(UserVO uvo);

    public void remove(String email);

    public int loginCheck(UserVO uvo);
}
