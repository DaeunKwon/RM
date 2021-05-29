package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ProjectInDAO;
import com.example.demo.domain.ProjectInVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectInServiceImp implements ProjectInService {

    @Autowired
    private ProjectInDAO pINdao;

    @Override
    public void add(ProjectInVO pINvo) {
        pINdao.insert(pINvo);
    }

    @Override
    public List<ProjectInVO> getLedaerList() {
        return pINdao.getLeaderList();
    }

}
