package com.example.demo.ctrl;

import java.util.List;
import org.springframework.http.MediaType;

import com.example.demo.domain.ReportDetailVO;
import com.example.demo.domain.ReportVO;
import com.example.demo.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(value = "/rptWrite")
    @ResponseBody
    public String postMethodName(@RequestBody ReportVO rvo) {
        log.info(">>>>>>>>>>>>>>>report 작성");
        reportService.write(rvo);
        return "index.html";
    }

    @PostMapping(value = "/write")
    @ResponseBody
    public void write(@RequestBody List<ReportDetailVO> reportDetail) {
        log.info("report detail:" + reportDetail.get(0).getRpt_content());
    }

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

}
