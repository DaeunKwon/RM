package com.example.demo.ctrl;

import com.example.demo.domain.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserCtrl {
    private final Logger log = LoggerFactory.getLogger(UserCtrl.class);

    @GetMapping(value = "/login")
    public String login() {
        return "index.html";
    }

    @GetMapping(value = "/join")
    public String join() {
        log.info(">>>>>>>>>>>>>>>>>>>>joinGET");
        return "index.html";
    }

    @PostMapping(value = "/joinPOST")
    public String joinPOST(@RequestParam("uvo", required=false) UserVO uvo) {
        log.info(">>>>>>>>>>>>>>>>>joinPOST");
        return "index.html";
    }

    // @PostMapping(value = "/joinPOST")
    // public String joinPOST(@RequestParam(value = "uvo", required = false) UserVO
    // uvo) {
    // log.info(">>>>>>>>>>>>>> joinPOST");
    // System.out.println(uvo.toString());
    // return "index.html";
    // }

}
