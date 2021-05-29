package com.example.demo.dao.impl;

import java.util.List;

import com.example.demo.dao.ProjectInDAO;
import com.example.demo.domain.ProjectInVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class ProjectInDAOImp implements ProjectInDAO {

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.projectIn";

    @Override
    public void insert(ProjectInVO pINvo) {
        sql.insert(ns + ".addLeader", pINvo);
    }

    @Override
    public List<ProjectInVO> getLeaderList() {
        return sql.selectList(ns + ".getLeaderList");
    }

}
