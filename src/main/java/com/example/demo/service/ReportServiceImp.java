package com.example.demo.service;

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
    public List<ReportDetailVO> getReportDetailList() {
        return rdao.getReportDetailList();

    }

    @Override
    public void writeDetail(List<ReportDetailVO> reportDetailList) {
        rdao.writeDetail(reportDetailList);
    }

}
