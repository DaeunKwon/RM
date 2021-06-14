package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.dao.ReportDAO;
import com.example.demo.domain.ReportDetailVO;
import com.example.demo.domain.ReportVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImp implements ReportService {
    private final Logger log = LoggerFactory.getLogger(ReportServiceImp.class);

    @Autowired
    private ReportDAO rdao;

    @Override
    public int write(ReportVO rvo) {
        return rdao.write(rvo);
    }

    @Override
    public List<ReportVO> list() {
        log.info(">>>>>>>>>>>> report list service");
        return rdao.list();
    }

    @Override
    public List<ReportDetailVO> getReportDetailList(int prj_in_no, int prj_no, String authority) {
        return rdao.getReportDetailList(prj_in_no, prj_no, authority);

    }

    @Override
    public void writeDetail(List<ReportDetailVO> reportDetailList) {
        rdao.writeDetail(reportDetailList);
    }

    @Override
    public List<ReportDetailVO> getSelectedReport(Date rpt_write_d8) {
        return rdao.getSelectedReport(rpt_write_d8);
    }

    @Override
    public List<ReportDetailVO> getReportDetailList(String email) {
        return rdao.getReportDetailList(email);
    }

    @Override
    public List<ReportDetailVO> getAll() {
        return rdao.getAll();
    }

    @Override
    public List<ReportDetailVO> getADMIN(int prj_no) {
        return rdao.getADMIN(prj_no);
    }

    @Override
    public List<ReportDetailVO> getUSER(int prj_in_no) {
        return rdao.getUSER(prj_in_no);
    }

}
