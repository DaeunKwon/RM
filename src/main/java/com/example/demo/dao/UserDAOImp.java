package com.example.demo.dao;

import com.example.demo.domain.UserVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImp implements UserDAO {
    private final Logger log = LoggerFactory.getLogger(UserDAOImp.class);

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.dao.UserMapper";

    @Override
    public void join(UserVO uvo) {
        log.info(">>>>>>>>>>>>>>>>>userdao 진입");
        sql.insert(ns + ".join", uvo);
        log.info(">>>>>>>>>>>>>>>>>user 정보 넣기 성공");
    }

    @Override
    public void update(UserVO uvo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String email) {
        // TODO Auto-generated method stub

    }

    @Override
    public int loginCheck(UserVO uvo) {
        return sql.selectOne(ns + ".loginCheck", uvo);
    }

}
