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
    public int insert(ProjectVO pvo) {
        return sql.insert(ns + ".write", pvo);

    }

    @Override
    public void update(ProjectVO pvo) {
        sql.update(ns + ".update", pvo);
    }

    @Override
    public List<ProjectVO> getProjectList(String email, String authority) {
        if (authority.equals("ROLE_ROOT")) {
            return sql.selectList(ns + ".getAllProjectList");
        } else {
            return sql.selectList(ns + ".getProjectList", email);
        }
    }

    @Override
    public List<ProjectVO> getDoneProjectList(String email, String authority) {
        if (authority.equals("ROLE_ROOT")) {
            return sql.selectList(ns + ".getAllDoneProjectList");
        } else {
            return sql.selectList(ns + ".getDoneProjectList", email);
        }
    }

    @Override
    public void deleteProject(int prj_no) {
        sql.update(ns + ".deleteProject", prj_no);
    }

}
