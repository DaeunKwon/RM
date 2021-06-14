package com.example.demo.service;

import java.util.List;

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
    public boolean checkoffWork(ComVO cvo) {
        return comdao.checkoffWork(cvo);
    }

    @Override
    public List<ComVO> weekTime(ComVO cvo) {
        return comdao.weekTime(cvo);
    }
}
