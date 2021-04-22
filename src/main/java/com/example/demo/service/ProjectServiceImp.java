package com.example.demo.service;

import com.example.demo.dao.ProjectDAO;
import com.example.demo.domain.ProjectVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectDAO pdao;

    @Override
    public void write(ProjectVO pvo) {
        pdao.insert(pvo);
    }

    @Override
    public void modify(ProjectVO pvo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(ProjectVO pvo) {
        // TODO Auto-generated method stub

    }

}
