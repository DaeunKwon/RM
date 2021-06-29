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

    @Override
    public int prjpeople(ComVO cvo) {
        return comdao.prjpeople(cvo);
    }

    @Override
    public List<ComVO> ranklist(ComVO cvo) {
        return comdao.ranklist(cvo);
    }

    @Override
    public int gotocount(ComVO cvo) {
        return comdao.gotocount(cvo);
    }

    @Override
    public List<ComVO> monthlist(ComVO cvo) {
        return comdao.monthlist(cvo);
    }

    @Override
    public boolean rptCheck(ComVO cvo) {
        return comdao.rptCheck(cvo);

    }

    @Override
    public List<ComVO> monthNamelist(ComVO cvo) {
        return comdao.monthNamelist(cvo);

    }

    @Override
    public List<ComVO> worktime(ComVO cvo) {
        return comdao.worktime(cvo);
    }
}
