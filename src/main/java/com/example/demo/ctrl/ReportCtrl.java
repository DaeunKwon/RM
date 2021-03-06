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
        return reportService.list();
    }

    @ResponseBody
    @GetMapping(value = "/getAll")
    public List<ReportVO> getReportAll() {
        return reportService.getReportAll();
    }

    @ResponseBody
    @GetMapping(value = "/getADMIN")
    public List<ReportVO> getReportADMIN(int prj_no) {
        return reportService.getReportADMIN(prj_no);
    }

    @ResponseBody
    @GetMapping(value = "/getUSER")
    public List<ReportVO> getReportUSER(HttpServletRequest req) {
        return reportService.getReportUSER(Integer.parseInt(req.getParameter("prj_in_no")));
    }

    @ResponseBody
    @GetMapping(value = "/detail/getAll")
    public List<ReportDetailVO> getAll() {
        return reportService.getAll();
    }

    @ResponseBody
    @GetMapping(value = "/detail/getADMIN")
    public List<ReportDetailVO> getADMIN(int prj_no) {
        return reportService.getADMIN(prj_no);
    }

    @ResponseBody
    @GetMapping(value = "/detail/getUSER")
    public List<ReportDetailVO> getUSER(HttpServletRequest req) {
        return reportService.getUSER(Integer.parseInt(req.getParameter("prj_in_no")));
    }

    @ResponseBody
    @GetMapping(value = "/detail/setDetailList")
    public List<ReportDetailVO> setDetailList(int rpt_no) {
        return reportService.setDetailList(rpt_no);
    }

    @ResponseBody
    @GetMapping(value = "/detail/list")
    public List<ReportDetailVO> getReportDetailList(String email) {
        return reportService.getReportDetailList(email);
    }

    @PostMapping(value = "/write")
    @ResponseBody
    public int write(HttpServletRequest req) {
        ReportVO rvo = new ReportVO();
        rvo.setPrj_no(Integer.parseInt(req.getParameter("prj_no")));
        rvo.setRpt_writer(Integer.parseInt(req.getParameter("rpt_writer")));
        rvo.setRpt_mod_writer(Integer.parseInt(req.getParameter("rpt_mod_writer")));
        reportService.write(rvo);
        return rvo.getRpt_no();
    }

    @PostMapping(value = { "/write/detail", "/update/detail" })
    public void writeDetail(HttpServletRequest req) throws ParseException {
        int flag = Integer.parseInt(req.getParameter("flag"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<ReportDetailVO> reportDetailList = new ArrayList<>();
        String[] rpt_start_time = flag > 0 ? req.getParameterValues("rpt_start_time")
                : req.getParameterValues("start_time");
        String[] rpt_end_time = flag > 0 ? req.getParameterValues("rpt_end_time") : req.getParameterValues("end_time");
        String[] rpt_content = flag > 0 ? req.getParameterValues("rpt_content") : req.getParameterValues("content");

        String[] rpt_no = req.getParameterValues("rpt_no");
        for (int i = 0; i < rpt_start_time.length; i++) {
            ReportDetailVO reportDetail = new ReportDetailVO();
            reportDetail.setRpt_start_time(df.parse(rpt_start_time[i]));
            reportDetail.setRpt_end_time(df.parse(rpt_end_time[i]));
            reportDetail.setRpt_content(rpt_content[i]);
            reportDetail.setRpt_no(Integer.parseInt(rpt_no[i]));
            reportDetailList.add(reportDetail);
        }
        reportService.writeDetail(reportDetailList, flag);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public void updateReport(HttpServletRequest req) {
        ReportVO report = new ReportVO();
        report.setRpt_no(Integer.parseInt(req.getParameter("rpt_no")));
        report.setRpt_mod_writer(Integer.parseInt(req.getParameter("rpt_mod_writer")));
        reportService.updateReport(report);
    }

    @GetMapping(value = "/get")
    public List<ReportDetailVO> getSelectedReport(HttpServletRequest req) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return reportService.getSelectedReport(df.parse(req.getParameter("rpt_write_d8")));
    }

}
