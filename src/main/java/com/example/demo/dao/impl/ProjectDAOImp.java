package com.example.demo.dao.impl;

import java.util.List;

import com.example.demo.dao.ProjectDAO;
import com.example.demo.domain.ProjectVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class ProjectDAOImp implements ProjectDAO {
    private final Logger log = LoggerFactory.getLogger(ProjectDAOImp.class);

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.project";

    @Override
    public void insert(ProjectVO pvo) {
        log.info(">>>>>project post dao");
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

    @Override
    public List<ProjectVO> getProjectList() {
        return sql.selectList(ns + ".getProjectList");
    }

    @Override
    public List<ProjectVO> getDoneProjectList() {
        return sql.selectList(ns + ".getDoneProjectList");
    }

}
