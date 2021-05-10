package com.example.demo.ctrl;

// import com.example.demo.domain.AuthorityVO;
// import com.example.demo.domain.ProjectInVO;
import com.example.demo.domain.ProjectVO;
// import com.example.demo.service.AuthService;
// import com.example.demo.service.ProjectInService;
import com.example.demo.service.ProjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController(value = "/api")
@Controller
public class ProjectCtrl {
    private final Logger log = LoggerFactory.getLogger(ProjectCtrl.class);

    @Autowired
    private ProjectService prjSvc;
    // private ProjectInService prjINSvc;
    // private AuthService authSvc;

    // @RequestMapping(value = "prjList", method = RequestMethod.GET)
    // @GetMapping(value = "/prjList")
    // public String list() {
    // log.info(">>>>>>>>>>>>>project list 페이지 출력");
    // return "index";
    // }

    // @RequestMapping(value = "prjWrite", method = RequestMethod.GET)
    // public String write() {
    // log.info(">>>>>>>>>>>>>>>>>>>>project write 페이지 출력");
    // return "index.html";
    // }

    @PostMapping(value = "/prjWrite", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void write(@RequestBody ProjectVO pvo) {
        log.info(">>>>>>>>>>>>project write POST 요청");
        prjSvc.write(pvo);
        log.info(">>>>>>>>>>>>project db에 넣기 성공");

    }

    @GetMapping(value = "/prjDetail")
    public String detail() {
        log.info(">>>>>>>>>>>>>project detail 페이지 출력");
        return "index.html";
    }
}
