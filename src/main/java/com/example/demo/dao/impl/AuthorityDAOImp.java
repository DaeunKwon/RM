package com.example.demo.dao.impl;

import com.example.demo.dao.AuthorityDAO;
import com.example.demo.domain.AuthorityVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class AuthorityDAOImp implements AuthorityDAO {

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.auth";

    @Override
    public void write(AuthorityVO authvo) {
        sql.insert(ns + ".write", authvo);
    }

}
