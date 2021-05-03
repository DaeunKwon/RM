package com.example.demo.ctrl;

import java.util.List;

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
@EnableAutoConfiguration
@RestController
// @RequestMapping(value = "/api/report")
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

    @ResponseBody
    @GetMapping(value = "api/report/list")
    public ResponseEntity<?> list() {
        log.info(">>>>>>>>>>>>>>>>report list 페이지 출력");
        log.info(">>>>>>>>>>> rlist: " + rptSvc.list());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/rptList")
    public ResponseEntity<?> rptlist() {
        log.info(">>>>>>>>>>>>>>>>report list 페이지 출력");
        log.info(">>>>>>>>>>> rlist: " + rptSvc.list());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
