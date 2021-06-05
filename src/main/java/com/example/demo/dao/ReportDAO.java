package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.ReportDetailVO;
import com.example.demo.domain.ReportVO;

public interface ReportDAO {
    public int write(ReportVO rvo);

    public List<ReportVO> list();

    public List<ReportDetailVO> getReportDetailList();

    public void writeDetail(List<ReportDetailVO> reportDetailList);

}
