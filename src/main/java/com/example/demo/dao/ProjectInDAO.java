package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.ProjectInVO;

public interface ProjectInDAO {
    public void insert(ProjectInVO pINvo);

    public List<ProjectInVO> getLeaderList();

    public ProjectInVO getLeaderInfo(int prj_no);

    public void addFollower(List<ProjectInVO> projectInList);

    public List<ProjectInVO> getFollowerInfo(int prj_no);

    public List<ProjectInVO> getUserProjectInfo(String email);

    public void updateLeader(ProjectInVO pINvo);

    public void updateFollower(List<ProjectInVO> projectInList);

}
