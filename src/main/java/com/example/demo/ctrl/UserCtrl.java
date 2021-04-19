package com.example.demo.ctrl;

import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserCtrl {
    private final Logger log = LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    private UserService userSvc;

    @GetMapping(value = "/")
    public String home() {
        return "index.html";
    }

    // @GetMapping
    // public ModelAndView index() {
    // ModelAndView mav = new ModelAndView();
    // mav.setViewName("index");
    // return mav;
    // }

    @GetMapping(value = "login")
    public String login() {
        return "index.html";
    }

    @RequestMapping(value = "join", method = RequestMethod.GET)
    public ModelAndView join() {
        log.info(">>>>>>>>>>>>>join 페이지 출력");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("join");
        return mav;
    }

    // @GetMapping(value = "/join")
    // public String join() {
    // log.info(">>>>>>>>>>>>>>>>>>>>joinGET");
    // return "index.html";
    // }

    @PostMapping(value = "/joinPOST")
    @ResponseBody
    public String joinPOST(@RequestBody UserVO uvo) {
        log.info(">>>>>>>>>>>>>>>>>joinPOST");
        log.info(uvo.getEmail());
        userSvc.join(uvo);
        log.info(">>>>>>>>>>>>>>>>>join 성공");
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