package com.example.demo.dao;

import com.example.demo.domain.UserVO;

public interface UserDAO {
    public void join(UserVO uvo);

    public void update(UserVO uvo);

    public void delete(String email);

    public UserVO loginCheck(String email);

}
