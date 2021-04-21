package com.example.demo.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectCtrl {
    private final Logger log = LoggerFactory.getLogger(ProjectCtrl.class);

    @RequestMapping(value = "prjList", method = RequestMethod.GET)
    public String list() {
        log.info(">>>>>>>>>>>>>project list 페이지 출력");
        return "index.html";
    }

    // @RequestMapping(value = "prjWrite", method = RequestMethod.GET)
    // public String write() {
    // log.info(">>>>>>>>>>>>>>>>>>>>project write 페이지 출력");
    // return "index.html";
    // }

}
