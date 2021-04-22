package com.example.demo.dao;

import com.example.demo.domain.ProjectVO;

public interface ProjectDAO {
    public void insert(ProjectVO pvo);

    public void update(ProjectVO pvo);

    public void delete(Integer prj_no);

}
