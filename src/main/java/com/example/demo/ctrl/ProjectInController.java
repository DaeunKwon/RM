package com.example.demo.ctrl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.domain.ProjectInVO;
import com.example.demo.service.ProjectInService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/project/in")
public class ProjectInController {
    private final Logger log = LoggerFactory.getLogger(ProjectInController.class);

    @Autowired
    private ProjectInService projectINService;

    @PostMapping(value = "/leader")
    @ResponseBody
    public void write(HttpServletRequest req) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ProjectInVO pINvo = new ProjectInVO();
        pINvo.setEmail(req.getParameter("email"));
        pINvo.setPrj_in_d8(df.parse(req.getParameter("prj_in_d8")));
        pINvo.setPrj_out_d8(df.parse(req.getParameter("prj_out_d8")));
        pINvo.setPrj_no(Integer.parseInt(req.getParameter("prj_no")));
        projectINService.add(pINvo);

    }

    @PostMapping(value = "/follower", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addFollower(HttpServletRequest req) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        log.info(">>>>>> add follower contoroller");
        List<ProjectInVO> projectInList = new ArrayList<>();
        String[] email = req.getParameterValues("email");
        String[] prj_in_d8 = req.getParameterValues("prj_in_d8");
        String[] prj_out_d8 = req.getParameterValues("prj_out_d8");
        String[] prj_no = req.getParameterValues("prj_no");
        for (int i = 0; i < email.length; i++) {
            ProjectInVO pINvo = new ProjectInVO();
            log.info(">>>>>>>>email:" + email[i]);
            log.info(">>>>>>>>email:" + prj_in_d8[i]);
            log.info(">>>>>>>>email:" + prj_out_d8[i]);
            pINvo.setEmail(email[i]);
            pINvo.setPrj_in_d8(df.parse(prj_in_d8[i]));
            pINvo.setPrj_out_d8(df.parse(prj_out_d8[i]));
            pINvo.setPrj_no(Integer.parseInt(prj_no[i]));
            projectInList.add(pINvo);
            // log.info(">>>>>>>>email:" + prj_no[i]);
        }
        for (ProjectInVO pvo : projectInList) {
            log.info(">>>>>>>>pinvo:" + pvo);
        }
        projectINService.addFollower(projectInList);
    }

    @GetMapping(value = "/leader/list")
    public List<ProjectInVO> getLeaderList(Model model) {
        log.info(">>>>>>>get leader list in project");
        return projectINService.getLedaerList();
    }

    @GetMapping(value = "/leader/info")
    public ProjectInVO getLeaderInfo(int prj_no) {
        log.info(">>>>>>>>getleaderinfo controller");
        return projectINService.getLeaderInfo(prj_no);
    }

    @GetMapping(value = "/follower/info")
    public List<ProjectInVO> getFollowerInfo(int prj_no) {
        log.info(">>>>>>> get follower info controller");
        return projectINService.getFollowerInfo(prj_no);
    }

}
