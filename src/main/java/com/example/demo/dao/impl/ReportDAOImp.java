package com.example.demo.dao.impl;

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
    public void write(ReportVO rvo) {
        log.info(">>>>>>>>>>>>report dao 진입");
        sql.insert(ns + ".write", rvo);
        log.info(">>>>>>>>>>>>>report db에 넣기 성공");
    }

    @Override
    public List<ReportVO> list() {
        log.info(">>>>>>>>>>>report list dao");
        return sql.selectList(ns + ".list");
    }

    @Override
    public List<ReportDetailVO> getReportDetailList() {
        return sql.selectList(ns + ".getReportDetailList");
    }

}
