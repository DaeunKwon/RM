package com.example.demo.ctrl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.domain.ProjectInVO;
import com.example.demo.service.ProjectInService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        projectINService.add(pINvo);

    }

}
