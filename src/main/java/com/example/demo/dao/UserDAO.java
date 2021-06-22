package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.UserVO;

public interface UserDAO {
    public void join(UserVO uvo);

    public UserVO getUserfindByEmail(String email);

    public List<UserVO> getUserList();

    public UserVO getUserfindByUser(UserVO user);

}
