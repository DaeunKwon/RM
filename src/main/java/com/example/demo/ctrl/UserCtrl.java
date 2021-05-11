package com.example.demo.ctrl;

import com.example.demo.dao.UserAuthoritiesDAO;
import com.example.demo.domain.Authorities;
import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
        private UserService userService;

        @Autowired
        private UserAuthoritiesDAO authoritiesDAO;

        // @RequestMapping(value = "/joinPOST", method = RequestMethod.POST)
        @PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public void joinPOST(@RequestBody UserVO uvo) {
                log.info(">>>>>>>>>>>>>>>>>joinPOST");
                log.info(uvo.getEmail());
                log.info(uvo.getPassword());
                userService.join(uvo);
                authoritiesDAO.insertAuthority(Authorities.builder().member_email(uvo.getEmail())
                                .roles_authority("ROLE_USER").build());
                log.info(">>>>>>>>>>>>>>>>>join 성공");
        }

        @PostMapping(value = "/emailCheck", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public boolean emailCheck(@RequestBody String email) {
                log.info(">>>>>return : " + userService.emailCheck(email));
                return userService.emailCheck(email);
        }

}