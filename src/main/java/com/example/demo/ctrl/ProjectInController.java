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

    @PostMapping(value = { "/leader", "/update/leader" })
    @ResponseBody
    public void write(HttpServletRequest req) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ProjectInVO pINvo = new ProjectInVO();

        pINvo.setEmail(req.getParameter("email"));
        pINvo.setPrj_in_d8(df.parse(req.getParameter("prj_in_d8")));
        pINvo.setPrj_out_d8(df.parse(req.getParameter("prj_out_d8")));
        pINvo.setPrj_no(Integer.parseInt(req.getParameter("prj_no")));
        int flag = Integer.parseInt(req.getParameter("flag"));
        projectINService.add(pINvo, flag);

    }

    @PostMapping(value = { "/follower", "/update/follower" })
    @ResponseBody
    public void addFollower(HttpServletRequest req) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<ProjectInVO> projectInList = new ArrayList<>();
        String[] email = req.getParameterValues("email");
        String[] prj_in_d8 = req.getParameterValues("prj_in_d8");
        String[] prj_out_d8 = req.getParameterValues("prj_out_d8");
        String[] prj_no = req.getParameterValues("prj_no");
        int flag = Integer.parseInt(req.getParameter("flag"));
        for (int i = 0; i < email.length; i++) {
            ProjectInVO pINvo = new ProjectInVO();
            pINvo.setEmail(email[i]);
            pINvo.setPrj_in_d8(df.parse(prj_in_d8[i]));
            pINvo.setPrj_out_d8(df.parse(prj_out_d8[i]));
            pINvo.setPrj_no(Integer.parseInt(prj_no[i]));
            projectInList.add(pINvo);
        }
        int num = Integer.parseInt(prj_no[0]);
        projectINService.addFollower(projectInList, flag, num);
    }

    @GetMapping(value = "/leader/list")
    public List<ProjectInVO> getLeaderList(Model model) {
        return projectINService.getLedaerList();
    }

    @GetMapping(value = "/leader/info")
    public ProjectInVO getLeaderInfo(int prj_no) {
        return projectINService.getLeaderInfo(prj_no);
    }

    @GetMapping(value = "/follower/info")
    public List<ProjectInVO> getFollowerInfo(int prj_no) {
        return projectINService.getFollowerInfo(prj_no);
    }

    @GetMapping(value = "/info")
    public List<ProjectInVO> getUserProjectInfo(HttpServletRequest req) {
        return projectINService.getUserProjectInfo(req.getParameter("email"));
    }

}
