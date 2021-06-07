package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ProjectInVO;

public interface ProjectInService {

    public void add(ProjectInVO pINvo);

    public List<ProjectInVO> getLedaerList();

    public ProjectInVO getLeaderInfo(int prj_no);

    public void addFollower(List<ProjectInVO> projectInList);

    public List<ProjectInVO> getFollowerInfo(int prj_no);

}
