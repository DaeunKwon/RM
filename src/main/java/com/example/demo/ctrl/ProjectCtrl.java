package com.example.demo.ctrl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

// import com.example.demo.domain.AuthorityVO;
// import com.example.demo.domain.ProjectInVO;
import com.example.demo.domain.ProjectVO;
// import com.example.demo.service.AuthService;
// import com.example.demo.service.ProjectInService;
import com.example.demo.service.ProjectService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*")
// @RestController(value = "/api/project")
@RestController
@RequestMapping(value = "/api/project")
public class ProjectCtrl {
    private final Logger log = LoggerFactory.getLogger(ProjectCtrl.class);

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/write")
    @ResponseBody
    public int write(HttpServletRequest req) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ProjectVO pvo = new ProjectVO();
        pvo.setPrj_title(req.getParameter("title"));
        pvo.setCond(req.getParameter("cond"));
        pvo.setStart_d8(df.parse(req.getParameter("start_date")));
        pvo.setEnd_d8(df.parse(req.getParameter("end_date")));
        pvo.setPrj_writer(req.getParameter("writer"));
        pvo.setPrj_mod_writer(req.getParameter("mod_writer"));
        pvo.setPrj_content(req.getParameter("content"));
        pvo.setPrj_remark(req.getParameter("remark"));
        log.info(">>>>>>> project: " + pvo);
        projectService.write(pvo);
        return pvo.getPrj_no();
        // log.info(">>>>>>>>>>>>project db에 넣기 성공");

    }

    @ResponseBody
    @GetMapping(value = "/main")
    public List<ProjectVO> list(Model model) {
        log.info(">>>>>>project list get");
        return projectService.getProjectList();

    }

    @PostMapping(value = "/write", produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseBody
    public void write(@RequestBody ProjectVO pvo) {
        log.info(">>>>>>>>>>>>project write POST 요청");
        projectService.write(pvo);
        log.info(">>>>>>>>>>>>project db에 넣기 성공");

    }

    @GetMapping(value = "/detail")
    public String detail() {
        log.info(">>>>>>>>>>>>>project detail 페이지 출력");
        return "index.html";

    @ResponseBody
    @GetMapping(value = "/main/done")
    public List<ProjectVO> getDoneProjectlist(Model model) {
        log.info(">>>>>>project list get");
        return projectService.getDoneProjectList();

    }

    @ResponseBody
    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProject(@RequestBody int prj_no) {
        log.info(">>>>>delete project controller");
        projectService.deleteProject(prj_no);

    }

    @ResponseBody
    @GetMapping(value = "/api/project/main")
    public List<ProjectVO> list(Model model) {
        log.info(">>>>>>project list get");
        return projectService.getProjectList();

    }

    @ResponseBody
    @GetMapping(value = "/api/project/main/done")
    public List<ProjectVO> getDoneProjectlist(Model model) {
        log.info(">>>>>>project list get");
        return projectService.getDoneProjectList();

    }
}
