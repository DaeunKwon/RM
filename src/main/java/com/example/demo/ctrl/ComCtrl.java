package com.example.demo.ctrl;

import java.util.List;

import com.example.demo.domain.ComVO;
import com.example.demo.service.ComService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/commute")
public class ComCtrl {
    private final Logger log = LoggerFactory.getLogger(ComCtrl.class);

    @Autowired
    private ComService comservice;

    @GetMapping(value = "/com_main")
    public String com_main(Model model) {
        log.info(">>>>>>com_main");
        return "index.html";

    }

    @GetMapping(value = "/com_detail")
    public String com_detail(Model model) {
        log.info(">>>>>>com_detail");
        return "index.html";

    }

    @PostMapping(value = "/gotoWork", produces = MediaType.APPLICATION_JSON_VALUE)
    public void gotoWork(@RequestBody ComVO cvo) {
        log.info("<<<<<<<<<<<<< ComCtrl 진입");

        log.info(">>>>>>>>>>>>> cvo.getCom_d8: " + cvo.getCom_d8());
        log.info(">>>>>>>>>>>>> cvo.getPrj_no: " + cvo.getPrj_no());
        comservice.gotoWork(cvo);

    }

    @PostMapping(value = "/checkWork")
    public boolean checkWork(@RequestBody ComVO cvo) {
        log.info(">>>>>>>>>>>>>>>checkWork cvo.getCom_d8: " + cvo.getCom_d8());
        return comservice.checkWork(cvo);
    }

    @PostMapping(value = "/checkoffWork")
    public boolean checkoffWork(@RequestBody ComVO cvo) {
        log.info(">>>>>>>>>>>>>>>checkoffWork cvo.getCom_d8: " + cvo.getCom_d8());
        return comservice.checkoffWork(cvo);
    }

    @PostMapping(value = "/offWork", produces = MediaType.APPLICATION_JSON_VALUE)
    public void offWork(@RequestBody ComVO cvo) {
        log.info("<<<<<<<<<<<<< ComCtrl 진입");

        log.info(">>>>>>>>>>>>> cvo.getCom_d8: " + cvo.getCom_d8());
        comservice.offWork(cvo);
    }

    @PostMapping(value = "/weekTime", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ComVO> weekTime(@RequestBody ComVO cvo) {
        log.info("<<<<<<<<<<<<< ComCtrl 진입");
        log.info(">>>>>>>>>>>>> cvo.getCom_d8: " + cvo.getCom_d8());
        return comservice.weekTime(cvo);
    }
}
