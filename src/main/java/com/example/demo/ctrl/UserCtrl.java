package com.example.demo.ctrl;

import javax.servlet.http.HttpSession;

import com.example.demo.dao.CustomUserDetails;
import com.example.demo.dao.UserAuthoritiesDAO;
import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

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
    // public ResponseEntity<?> login(@RequestBody UserVO uvo, HttpSession session)
    // {
    // log.info(">>>>>>>>>>>>>>>>>>login post userctrl 진입");
    // UserVO user = userSvc.loginCheck(uvo.getEmail());
    // log.info(">>>>>>>>>>>> user: "+user);
    // log.info(">>>>>>>>>>>>>>>>>>>로그인 검사 완료");
    // UsernamePasswordAuthenticationToken token =
    // new UsernamePasswordAuthenticationToken(uvo.getEmail(), uvo.getPassword());

    // Authentication authentication = authenticationManager.authenticate(token);

    // SecurityContextHolder.getContext().setAuthentication(authentication);

    // session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
    // SecurityContextHolder.getContext());

    // user.setTokenId(RequestContextHolder.currentRequestAttributes().getSessionId());

    // return ResponseEntity.ok(HttpStatus.OK);
    // }

}