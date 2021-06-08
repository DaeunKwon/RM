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
        log.info(">>>>>project post service");
        return pdao.insert(pvo);
    }

    @Override
    public void modify(ProjectVO pvo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(ProjectVO pvo) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ProjectVO> getProjectList(String email) {
        return pdao.getProjectList(email);
    }

    @Override
    public List<ProjectVO> getDoneProjectList(String email) {
        return pdao.getDoneProjectList(email);
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
