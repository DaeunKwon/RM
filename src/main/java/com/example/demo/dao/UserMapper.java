package com.example.demo.dao;

import com.example.demo.domain.UserVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    void join(UserVO uvo);

    UserVO loginCheck(String email);
}
