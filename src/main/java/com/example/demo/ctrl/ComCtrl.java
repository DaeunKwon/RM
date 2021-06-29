package com.example.demo.ctrl;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
        return "index.html";

    }

    @GetMapping(value = "/com_detail")
    public String com_detail(Model model) {
        return "index.html";

    }

    @PostMapping(value = "/gotoWork", produces = MediaType.APPLICATION_JSON_VALUE)
    public void gotoWork(@RequestBody ComVO cvo) {
        comservice.gotoWork(cvo);

    }

    @PostMapping(value = "/checkWork", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkWork(@RequestBody ComVO cvo) {
        return comservice.checkWork(cvo);
    }

    @PostMapping(value = "/checkoffWork")
    public boolean checkoffWork(@RequestBody ComVO cvo) {
        return comservice.checkoffWork(cvo);
    }

    @PostMapping(value = "/offWork", produces = MediaType.APPLICATION_JSON_VALUE)
    public void offWork(@RequestBody ComVO cvo) {
        comservice.offWork(cvo);
    }

    @PostMapping(value = "/weekTime", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ComVO> weekTime(@RequestBody ComVO cvo) {
        return comservice.weekTime(cvo);
    }

    @PostMapping(value = "/prjpeople")
    public int prjpeople(@RequestBody ComVO cvo) {
        return comservice.prjpeople(cvo);
    }

    @GetMapping(value = "/rank")
    public List<ComVO> ranklist(HttpServletRequest req) throws ParseException {
        ComVO cvo = new ComVO();
        cvo.setCom_d8(req.getParameter("com_d8"));
        cvo.setPrj_no(Integer.parseInt(req.getParameter("prj_no")));
        return comservice.ranklist(cvo);
    }

    @GetMapping(value = "/gotocount")
    public int gotocount(ComVO cvo) {
        return comservice.gotocount(cvo);
    }

    @PostMapping(value = "/monthlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ComVO> monthlist(@RequestBody ComVO cvo) {
        return comservice.monthlist(cvo);
    }

    @PostMapping(value = "/rptCheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean rptCheck(@RequestBody ComVO cvo) {
        return comservice.rptCheck(cvo);
    }

    @GetMapping(value = "/monthNamelist")
    public List<ComVO> monthNamelist(ComVO cvo) {
        return comservice.monthNamelist(cvo);
    }

    @PostMapping(value = "/worktime", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ComVO> worktime(@RequestBody ComVO cvo) {
        return comservice.worktime(cvo);
    }
}
