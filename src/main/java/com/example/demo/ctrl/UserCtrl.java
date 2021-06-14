package com.example.demo.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.dao.UserAuthoritiesDAO;
import com.example.demo.domain.Authorities;
import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
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
                if (uvo.getEmail().equals("root")) {
                        authoritiesDAO.insertAuthority(Authorities.builder().member_email(uvo.getEmail())
                                        .roles_authority("ROLE_ROOT").build());
                        log.info(">>>>>>>>>>>>>>>>>root join 성공");
                } else {
                        authoritiesDAO.insertAuthority(Authorities.builder().member_email(uvo.getEmail())
                                        .roles_authority("ROLE_USER").build());
                        log.info(">>>>>>>>>>>>>>>>>join 성공");
                }

        }

        @PostMapping(value = "/emailCheck", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public boolean emailCheck(@RequestBody String email) {
                log.info(">>>>>return : " + userService.emailCheck(email));
                return userService.emailCheck(email);
        }

        @ResponseBody
        @GetMapping(value = "/list")
        public List<UserVO> userList() {
                log.info(">>>>>> get user list");
                log.info(">>>>>>>>user list: " + userService.getlist());
                return userService.getlist();
        }

        @GetMapping(value = "/info")
        public UserVO userInfo(HttpServletRequest request) {
                log.info(">>>>>>> auth login controller");
                HttpSession session = request.getSession();
                log.info(">>> " + session);
                UserVO user = (UserVO) session.getAttribute("user");
                log.info(">>> " + user);
                return user;
        }

}