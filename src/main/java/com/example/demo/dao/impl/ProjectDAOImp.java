package com.example.demo.dao.impl;

import com.example.demo.dao.ProjectDAO;
import com.example.demo.domain.ProjectVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class ProjectDAOImp implements ProjectDAO {

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.project";

    @Override
    public void insert(ProjectVO pvo) {
        sql.insert(ns + ".write", pvo);

    }

    @Override
    public void update(ProjectVO pvo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer prj_no) {
        // TODO Auto-generated method stub

    }

}
