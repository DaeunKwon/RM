package com.example.demo.dao;

import com.example.demo.domain.ProjectInVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectInDAOImp implements ProjectInDAO {

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.dao.ProjectInMapper";

    @Override
    public void insert(ProjectInVO pINvo) {
        sql.insert(ns + ".write", pINvo);
    }

}
