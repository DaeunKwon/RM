package com.example.demo.dao.impl;

import java.util.List;

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
    public boolean checkoffWork(ComVO cvo) {
        return sql.selectOne(ns + ".checkoffWork", cvo) != null;
    }

    @Override
    public List<ComVO> weekTime(ComVO cvo) {
        log.info(">>>>>>>>>>>>>>comdao weekTime 진입");
        log.info(cvo.getCom_d8());
        return sql.selectList(ns + ".weekTime", cvo);
    }

    @Override
    public int prjpeople(int prj_no) {
        log.info(">>>>>>>>>>>>>> comdao prjpeople 진입");
        log.info(">>>>>>>>>>>>>> prj_no=" + prj_no);
        return sql.selectOne(ns + ".prjpeople", prj_no);
    }

    @Override
    public List<ComVO> ranklist(ComVO cvo) {
        log.info(">>>>>>>>>>>>> comdao ranklist 진입");
        log.info(">>>>>>>>>>>>> com_d8=" + cvo);
        return sql.selectList(ns + ".ranklist", cvo);
    }

    @Override
    public int gotocount(ComVO cvo) {
        return sql.selectOne(ns + ".gotocount", cvo);
    }

    @Override
    public List<ComVO> monthlist(ComVO cvo) {
        return sql.selectList(ns + ".monthlist", cvo);
    }

    @Override
    public boolean rptCheck(ComVO cvo) {
        return sql.selectOne(ns + ".rptCheck", cvo) != null;
    }

    @Override
    public List<ComVO> monthNamelist(ComVO cvo) {
        return sql.selectList(ns + ".monthNamelist", cvo);
    }
}
