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
    public int prjpeople(int prj_no) {
        return comdao.prjpeople(prj_no);
    }

    @Override
    public List<ComVO> ranklist(String com_d8) {
        return comdao.ranklist(com_d8);
    }

    @Override
    public int gotocount(String com_d8) {
        return comdao.gotocount(com_d8);
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
    public List<ComVO> monthNamelist(String com_d8) {
        return comdao.monthNamelist(com_d8);

    }
}
