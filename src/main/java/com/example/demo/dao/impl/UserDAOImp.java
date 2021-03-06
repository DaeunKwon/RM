package com.example.demo.dao.impl;

import java.util.List;

import com.example.demo.dao.UserDAO;
import com.example.demo.domain.UserVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class UserDAOImp implements UserDAO {
    private final Logger log = LoggerFactory.getLogger(UserDAOImp.class);

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.user";

    @Override
    public void join(UserVO uvo) {
        sql.insert(ns + ".join", uvo);
    }

    @Override
    public UserVO getUserfindByEmail(String email) {
        return sql.selectOne(ns + ".getUserfindByEmail", email);
    }

    @Override
    public List<UserVO> getUserList() {
        return sql.selectList(ns + ".getUserList");
    }

    @Override
    public UserVO getUserfindByUser(UserVO user) {
        return sql.selectOne(ns + ".getUserfindByUser", user);
    }

}
