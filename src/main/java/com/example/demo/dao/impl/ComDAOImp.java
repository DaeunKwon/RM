package com.example.demo.dao.impl;

import com.example.demo.dao.ComDAO;
import com.example.demo.domain.ComVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public class ComDAOImp implements ComDAO {
    private final Logger log = LoggerFactory.getLogger(ComDAOImp.class);

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.commute";

    @Override
    public void gotoWork(ComVO cvo) {
        log.info(">>>>>>>>>>>>>>comdao gotoWork 진입");
        sql.insert(ns + ".gotoWork", cvo);
    }

    @Override
    public void offWork(ComVO cvo) {
        log.info(">>>>>>>>>>>>>>comdao offWork 진입");
        sql.update(ns + ".offWork", cvo);
    }

    @Override
    public boolean checkWork(ComVO cvo) {
        return sql.selectOne(ns + ".checkWork", cvo) != null;
    }

    @Override
    public void workTime(ComVO cvo) {
        sql.update(ns + ".workTime", cvo);
    }
}
