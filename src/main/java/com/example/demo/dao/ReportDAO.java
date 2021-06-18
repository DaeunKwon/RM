package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.ReportDetailVO;
import com.example.demo.domain.ReportVO;

public interface ReportDAO {
    public int write(ReportVO rvo);

    public List<ReportVO> list();

    public List<ReportDetailVO> getReportDetailList(int prj_in_no, int prj_no, String authority);

    public void writeDetail(List<ReportDetailVO> reportDetailList);

    public List<ReportDetailVO> getSelectedReport(Date rpt_write_d8);

    public List<ReportDetailVO> getReportDetailList(String email);

    public List<ReportDetailVO> getAll();

    public List<ReportDetailVO> getADMIN(int prj_no);

    public List<ReportDetailVO> getUSER(int prj_in_no);

    public void updateReport(ReportVO report);

    public void updateDetail(List<ReportDetailVO> reportDetailList);

    public List<ReportVO> getReportAll();

    public List<ReportVO> getReportADMIN(int prj_no);

    public List<ReportVO> getReportUSER(int prj_in_no);

}
