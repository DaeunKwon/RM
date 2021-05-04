package com.example.demo.ctrl;

import com.example.demo.dao.UserAuthoritiesDAO;
import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/user")
public class UserCtrl {
    private final Logger log = LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    private UserService userSvc;

    @Autowired
    private UserAuthoritiesDAO authoritiesDAO;

    // @RequestMapping(value = "/joinPOST", method = RequestMethod.POST)
    @PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void joinPOST(@RequestBody UserVO uvo) {
        log.info(">>>>>>>>>>>>>>>>>joinPOST");
        log.info(uvo.getEmail());
        log.info(uvo.getPassword());
        userSvc.join(uvo);
        log.info(">>>>>>>>>>>>>>>>>join 성공");
    }

    // @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    // @ResponseBody
    // public ResponseEntity<?> login(@RequestBody UserVO uvo) {
    // log.info(">>>>>>>>>>>>>>>>>>login post userctrl 진입");
    // int flag = userSvc.loginCheck(uvo);
    // log.info(Integer.toString(flag));
    // log.info(">>>>>>>>>>>>>>>>>>>로그인 검사 완료");
    // if (flag > 0) {
    // log.info(">>>>>>>>>>>>>>>이메일 있음 확인");
    // }
    // return new ResponseEntity<>(HttpStatus.OK);
    // }

}