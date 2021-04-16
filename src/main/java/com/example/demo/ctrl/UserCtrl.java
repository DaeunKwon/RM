package com.example.demo.ctrl;

import com.example.demo.domain.UserVO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserCtrl {

    @GetMapping(value = "/")
    public String login() {
        return "index.html";
    }

    @GetMapping(value = "/join")
    public String join() {
        return "index.html";
    }

    @PostMapping(value = "/joinPOST")
    public String joinPOST(@RequestParam(value = "uvo", required = false) UserVO uvo) {
        System.out.println(uvo.toString());
        return "index.html";
    }

}
