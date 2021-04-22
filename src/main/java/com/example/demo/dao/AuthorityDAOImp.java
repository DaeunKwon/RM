package com.example.demo.dao;

import com.example.demo.domain.AuthorityVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAOImp implements AuthorityDAO {

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.dao.AuthMapper";

    @Override
    public void write(AuthorityVO authvo) {
        sql.insert(ns + ".write", authvo);
    }

}
