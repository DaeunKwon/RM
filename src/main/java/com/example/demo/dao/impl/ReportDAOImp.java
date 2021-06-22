package com.example.demo.dao.impl;

import java.util.Date;
import java.util.List;

import com.example.demo.dao.ReportDAO;
import com.example.demo.domain.ReportDetailVO;
import com.example.demo.domain.ReportVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class ReportDAOImp implements ReportDAO {
    private final Logger log = LoggerFactory.getLogger(ReportDAOImp.class);

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.report";

    @Override
    public int write(ReportVO rvo) {
        return sql.insert(ns + ".write", rvo);
    }

    @Override
    public List<ReportVO> list() {
        log.info(">>>>>>>>>>>report list dao");
        return sql.selectList(ns + ".list");
    }

    @Override
    public void writeDetail(List<ReportDetailVO> reportDetailList) {
        sql.insert(ns + ".writeDetail", reportDetailList);
    }

    @Override
    public List<ReportDetailVO> getSelectedReport(Date rpt_write_d8) {
        return sql.selectList(ns + ".getSelectedReport", rpt_write_d8);
    }

    @Override
    public List<ReportDetailVO> getReportDetailList(String email) {
        return sql.selectList(ns + ".getReport", email);
    }

    @Override
    public List<ReportDetailVO> getAll() {
        return sql.selectList(ns + ".getAll");
    }

    @Override
    public List<ReportDetailVO> getADMIN(int prj_no) {
        return sql.selectList(ns + ".getADMIN", prj_no);
    }

    @Override
    public List<ReportDetailVO> getUSER(int prj_in_no) {
        return sql.selectList(ns + ".getUSER", prj_in_no);
    }

    @Override
    public void updateReport(ReportVO report) {
        sql.update(ns + ".updateReport", report);
    }

    @Override
    public void updateDetail(List<ReportDetailVO> reportDetailList) {
        sql.delete(ns + ".deleteDetail", reportDetailList.get(0).getRpt_no());
        sql.insert(ns + ".writeDetail", reportDetailList);
    }

    @Override
    public List<ReportVO> getReportAll() {
        return sql.selectList(ns + ".getReportAll");
    }

    @Override
    public List<ReportVO> getReportADMIN(int prj_no) {
        return sql.selectList(ns + ".getReportADMIN", prj_no);
    }

    @Override
    public List<ReportVO> getReportUSER(int prj_in_no) {
        return sql.selectList(ns + ".getReportUSER", prj_in_no);
    }

    @Override
    public List<ReportDetailVO> setDetailList(int rpt_no) {
        return sql.selectList(ns + ".setDetailList", rpt_no);
    }

}
