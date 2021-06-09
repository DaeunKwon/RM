package com.example.demo.ctrl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.domain.ProjectVO;
import com.example.demo.service.ProjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
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
    public List<ProjectVO> list(HttpServletRequest req) {
        log.info(">>>>>>project list get");
        String email = req.getParameter("email");
        return projectService.getProjectList(email);

    }

    @ResponseBody
    @GetMapping(value = "/main/done")
    public List<ProjectVO> getDoneProjectlist(HttpServletRequest req) {
        log.info(">>>>>>project list get");
        String email = req.getParameter("email");
        return projectService.getDoneProjectList(email);

    }

    @ResponseBody
    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProject(@RequestBody int prj_no) {
        log.info(">>>>>delete project controller");
        projectService.deleteProject(prj_no);
    }

    @ResponseBody
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProject(HttpServletRequest req) throws ParseException {
        log.info(">>>>>>>>> update project controller");
        log.info("start_date" + req.getParameter("start_date"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ProjectVO pvo = new ProjectVO();
        pvo.setPrj_no(Integer.parseInt(req.getParameter("prj_no")));
        pvo.setPrj_title(req.getParameter("title"));
        pvo.setCond(req.getParameter("cond"));
        pvo.setStart_d8(df.parse(req.getParameter("start_date")));
        pvo.setEnd_d8(df.parse(req.getParameter("end_date")));
        pvo.setPrj_mod_writer(req.getParameter("mod_writer"));
        pvo.setPrj_content(req.getParameter("content"));
        pvo.setPrj_remark(req.getParameter("remark"));
        projectService.update(pvo);
    }
}
