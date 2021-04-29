package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.ReportVO;

public interface ReportDAO {
    public void write(ReportVO rvo);

    public List<ReportVO> list();

}
