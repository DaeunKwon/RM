package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ProjectDAO;
import com.example.demo.domain.ProjectVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImp implements ProjectService {
    private final Logger log = LoggerFactory.getLogger(ProjectServiceImp.class);

    @Autowired
    private ProjectDAO pdao;

    @Override
    public int write(ProjectVO pvo) {
        return pdao.insert(pvo);
    }

    @Override
    public List<ProjectVO> getProjectList(String email, String authority) {
        return pdao.getProjectList(email, authority);
    }

    @Override
    public List<ProjectVO> getDoneProjectList(String email, String authority) {
        return pdao.getDoneProjectList(email, authority);
    }

    @Override
    public void deleteProject(int prj_no) {
        pdao.deleteProject(prj_no);
    }

    @Override
    public void update(ProjectVO pvo) {
        pdao.update(pvo);
    }

}
