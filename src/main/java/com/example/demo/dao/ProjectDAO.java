package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.ProjectVO;

public interface ProjectDAO {
    public void insert(ProjectVO pvo);

    public void update(ProjectVO pvo);

    public void delete(Integer prj_no);

    public List<ProjectVO> getProjectList();

}
