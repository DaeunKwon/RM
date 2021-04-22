package com.example.demo.ctrl;

import com.example.demo.domain.AuthorityVO;
import com.example.demo.domain.ProjectInVO;
import com.example.demo.domain.ProjectVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectCtrl {
    private final Logger log = LoggerFactory.getLogger(ProjectCtrl.class);

    @Autowired
    private ProjectVO pvo;
    private ProjectInVO pINvo;
    private AuthorityVO authvo;

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

    @PostMapping(value = "prjWrite")
    @ResponseBody
    public void write(@RequestBody ProjectVO pvo, @RequestBody ProjectInVO pINvo, @RequestBody AuthorityVO authvo) {

    }
}
