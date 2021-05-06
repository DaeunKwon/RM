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
        authoritiesDAO.insertAuthority(
                Authorities.builder().member_email(uvo.getEmail()).roles_authority("ROLE_USER").build());
        log.info(">>>>>>>>>>>>>>>>>join 성공");
    }

    // @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    // public void login() {

    // }

    // @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    // @ResponseBody
    // public ResponseEntity<?> login(@RequestBody UserVO uvo, HttpSession session)
    // {
    // log.info(">>>>>>>>>>>>>>>>>>login post userctrl 진입");
    // UserVO user = userService.loginCheck(uvo.getEmail());
    // log.info(">>>>>>>>>>>> user: " + user);
    // log.info(">>>>>>>>>>>>>>>>>>>로그인 검사 완료");
    // UsernamePasswordAuthenticationToken token = new
    // UsernamePasswordAuthenticationToken(uvo.getEmail(),
    // uvo.getPassword());

    // Authentication authentication = authenticationManager.authenticate(token);

    // SecurityContextHolder.getContext().setAuthentication(authentication);

    // session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
    // SecurityContextHolder.getContext());

    // //
    // user.setTokenId(RequestContextHolder.currentRequestAttributes().getSessionId());

    // return ResponseEntity.ok(HttpStatus.OK);
    // }

}