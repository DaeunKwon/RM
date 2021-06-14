package com.example.demo.ctrl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.domain.ReportDetailVO;
import com.example.demo.domain.ReportVO;
import com.example.demo.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/report")
public class ReportCtrl {
    private final Logger log = LoggerFactory.getLogger(ReportCtrl.class);

    @Autowired
    private ReportService reportService;

    @ResponseBody
    @GetMapping(value = "/list")
    public List<ReportVO> list() {
        log.info(">>>>>>>>>>>>>>>>report list 페이지 출력");
        return reportService.list();
    }

    @ResponseBody
    @GetMapping(value = "/detail/list")
    public List<ReportDetailVO> getReportDetailList() {
        log.info(">>>>>>>>get report detail list");
        return reportService.getReportDetailList();
    }

    @PostMapping(value = "/write")
    @ResponseBody
    public int write(HttpServletRequest req) {
        log.info(">>>>>> add report post");
        ReportVO rvo = new ReportVO();
        rvo.setPrj_no(Integer.parseInt(req.getParameter("prj_no")));
        rvo.setRpt_writer(Integer.parseInt(req.getParameter("rpt_writer")));
        rvo.setRpt_mod_writer(Integer.parseInt(req.getParameter("rpt_mod_writer")));
        reportService.write(rvo);
        return rvo.getRpt_no();
    }

    @PostMapping(value = "/write/detail")
    public void writeDetail(HttpServletRequest req) throws ParseException {
        log.info(">>>>>>> write report detail controller");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<ReportDetailVO> reportDetailList = new ArrayList<>();
        String[] rpt_start_time = req.getParameterValues("start_time");
        String[] rpt_end_time = req.getParameterValues("end_time");
        String[] rpt_content = req.getParameterValues("content");
        String[] rpt_no = req.getParameterValues("rpt_no");
        for (int i = 0; i < rpt_start_time.length; i++) {
            ReportDetailVO reportDetail = new ReportDetailVO();
            reportDetail.setRpt_start_time(df.parse(rpt_start_time[i]));
            reportDetail.setRpt_end_time(df.parse(rpt_end_time[i]));
            reportDetail.setRpt_content(rpt_content[i]);
            reportDetail.setRpt_no(Integer.parseInt(rpt_no[i]));
            reportDetailList.add(reportDetail);
        }
        reportService.writeDetail(reportDetailList);

    }

    @GetMapping(value = "/get")
    public List<ReportDetailVO> getSelectedReport(HttpServletRequest req) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return reportService.getSelectedReport(df.parse(req.getParameter("rpt_write_d8")));
    }

}
