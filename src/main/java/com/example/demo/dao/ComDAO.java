package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.ComVO;

public interface ComDAO {
    public void gotoWork(ComVO cvo);

    public void offWork(ComVO cvo);

    public boolean checkWork(ComVO cvo);

    public boolean checkoffWork(ComVO cvo);

    public List<ComVO> weekTime(ComVO cvo);

    public int prjpeople(int prj_no);

    public List<ComVO> ranklist(String com_d8);

    public int gotocount(String com_d8);

    public List<ComVO> monthlist(ComVO cvo);

    public boolean rptCheck(ComVO cvo);
}
