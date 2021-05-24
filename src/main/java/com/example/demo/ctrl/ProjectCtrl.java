package com.example.demo.ctrl;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*")
// @RestController(value = "/api/project")
@EnableAutoConfiguration
@RestController
public class ProjectCtrl {
    private final Logger log = LoggerFactory.getLogger(ProjectCtrl.class);

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/api/project/write", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void write(@RequestBody ProjectVO pvo) {
        log.info(">>>>>>> project: " + pvo.getPrj_title());
        projectService.write(pvo);
        log.info(">>>>>>>>>>>>project db에 넣기 성공");

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
