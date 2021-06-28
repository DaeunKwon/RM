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

    public List<ComVO> ranklist(ComVO cvo);

    public int gotocount(ComVO cvo);

    public List<ComVO> monthlist(ComVO cvo);

    public boolean rptCheck(ComVO cvo);

    public List<ComVO> monthNamelist(ComVO cvo);

    public List<ComVO> worktime(ComVO cvo);
}
