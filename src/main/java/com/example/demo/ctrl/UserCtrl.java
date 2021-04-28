package com.example.demo.ctrl;

import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCtrl {
    private final Logger log = LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    private UserService userSvc;

    @GetMapping(value = "/")
    public void home() {
        log.info(">>>>>>>>>home");
    }

    // @RequestMapping(value = "/join", method = { RequestMethod.POST })
    @PostMapping(value = "/api/user/join", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void join(@RequestBody UserVO uvo) {
        log.info(">>>>>>>>>>>>>>>>>join");
        log.info(">>>>>>>>>>>>>>>uvo: " + uvo.toString());
        log.info(uvo.getPassword());
        // userSvc.join(uvo);
        // log.info(">>>>>>>>>>>>>>>>>join 성공");
    }

    @PostMapping(value = "/api/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void login(@RequestBody UserVO uvo) {
        log.info(">>>>>>>>>>>>>>>>>>login post userctrl 진입");
        int flag = userSvc.loginCheck(uvo);
        log.info(Integer.toString(flag));
        log.info(">>>>>>>>>>>>>>>>>>>로그인 검사 완료");
        if (flag > 0) {
            log.info(">>>>>>>>>>>>>>>이메일 있음 확인");
        }
    }

}