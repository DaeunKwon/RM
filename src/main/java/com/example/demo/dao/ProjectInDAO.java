package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.ProjectInVO;

public interface ProjectInDAO {
    public void insert(ProjectInVO pINvo);

    public List<ProjectInVO> getLeaderList();

}
