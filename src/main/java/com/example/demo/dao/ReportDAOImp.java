package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.ReportVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDAOImp implements ReportDAO {
    private final Logger log = LoggerFactory.getLogger(ReportDAOImp.class);

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.dao.ReportMapper";

    @Override
    public void write(ReportVO rvo) {
        log.info(">>>>>>>>>>>>report dao 진입");
        sql.insert(ns + ".write", rvo);
        log.info(">>>>>>>>>>>>>report db에 넣기 성공");
    }

    @Override
    public List<ReportVO> selectList() {
        log.info(">>>>>>>>>>>>>>report dao list 진입");
        return sql.selectList(ns + ".list");

    }

    @Override
    public ReportVO selectOne(int rpt_no) {
        log.info(">>>>>>>>>report detail dao");
        return sql.selectOne(ns + ".detail");
    }

}
