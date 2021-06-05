package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ReportDetailVO;
import com.example.demo.domain.ReportVO;

public interface ReportService {
    public int write(ReportVO rvo);

    public List<ReportVO> list();

    public List<ReportDetailVO> getReportDetailList();

    public void writeDetail(List<ReportDetailVO> reportDetailList);

}
