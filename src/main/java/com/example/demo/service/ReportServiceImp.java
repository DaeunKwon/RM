package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ReportDAO;
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
    public void write(ReportVO rvo) {
        log.info(">>>>>>>>>>>> report service 진입");
        rdao.write(rvo);
        log.info(">>>>>>>>>>>>>report service 성공");
    }

    @Override
    public List<ReportVO> list() {
        log.info(">>>>>>>>>>>>>>>>>>report service list 진입");
        return rdao.selectList();
    }

}
