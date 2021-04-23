package com.example.demo.ctrl;

import com.example.demo.domain.ReportVO;
import com.example.demo.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportCtrl {
    private final Logger log = LoggerFactory.getLogger(ReportCtrl.class);

    @Autowired
    private ReportService rptSvc;

    @PostMapping(value = "/rptWrite")
    @ResponseBody
    public String postMethodName(@RequestBody ReportVO rvo) {
        log.info(">>>>>>>>>>>>>>>report 작성");
        rptSvc.write(rvo);
        return "index.html";
    }

    @RequestMapping(value = "/rptList")
    public String list() {
        log.info(">>>>>>>>>>>>>>>>report list 페이지 출력");
        return "index.html";
    }

}
