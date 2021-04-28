package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.ReportVO;

public interface ReportService {
    public void write(ReportVO rvo);

    public List<ReportVO> list();

    public ReportVO detail(int rpt_no);

}
