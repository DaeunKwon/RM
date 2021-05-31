package com.example.demo.service;

import com.example.demo.dao.ComDAO;
import com.example.demo.domain.ComVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComServiceImp implements ComService {

    @Autowired
    ComDAO comdao;

    @Override
    public void gotoWork(ComVO cvo) {
        comdao.gotoWork(cvo);
    }

    @Override
    public void offWork(ComVO cvo) {
        comdao.offWork(cvo);
    }

    @Override
    public boolean checkWork(ComVO cvo) {
        return comdao.checkWork(cvo);
    }

    @Override
    public void workTime(ComVO cvo) {
        comdao.workTime(cvo);
    }
}
