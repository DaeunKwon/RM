package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ProjectVO;

public interface ProjectService {
    public int write(ProjectVO pvo);

    public void modify(ProjectVO pvo);

    public void remove(ProjectVO pvo);

    public List<ProjectVO> getProjectList(String email, String authority);

    public List<ProjectVO> getDoneProjectList(String email, String authority);

    public void deleteProject(int prj_no);

    public void update(ProjectVO pvo);

}
