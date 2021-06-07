package com.example.demo.dao.impl;

import java.util.List;

import com.example.demo.dao.ProjectInDAO;
import com.example.demo.domain.ProjectInVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class ProjectInDAOImp implements ProjectInDAO {
    private final Logger log = LoggerFactory.getLogger(ProjectInDAOImp.class);

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

    @Override
    public ProjectInVO getLeaderInfo(int prj_no) {
        log.info(">>>>>>>project in vo dao");
        return sql.selectOne(ns + ".getLeaderInfo", prj_no);
    }

    @Override
    public void addFollower(List<ProjectInVO> projectInList) {
        sql.insert(ns + ".addFollower", projectInList);
    }

    @Override
    public List<ProjectInVO> getFollowerInfo(int prj_no) {
        return sql.selectList(ns + ".getFollowerInfo", prj_no);
    }

}
