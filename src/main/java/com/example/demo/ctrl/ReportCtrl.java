package com.example.demo.ctrl;

import java.util.List;

import com.example.demo.domain.ReportVO;
import com.example.demo.service.ReportService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
// @RequestMapping("/report")
public class ReportCtrl {
    private final Logger log = LoggerFactory.getLogger(ReportCtrl.class);

    @Autowired
    private ReportService rptSvc;

    /**
     * 업무 일지 목록 조회
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping(value = "/rptList", headers = "Accept=application/json")
    public void list(Model model) throws Exception {
        log.info(">>>>>>>>>>>>>>>>report list 페이지 출력");
        List<ReportVO> rlist = rptSvc.list();

        JSONObject listObj = new JSONObject();
        JSONArray list = new JSONArray();
        for (int i = 0; i < rlist.size(); i++) {

            JSONObject vo = new JSONObject();

            vo.put("rpt_no", rlist.get(i).getRpt_no());
            vo.put("prj_no", rlist.get(i).getPrj_no());
            vo.put("rpt_write_d8", rlist.get(i).getRpt_write_d8());
            vo.put("rpt_writer", rlist.get(i).getRpt_writer());

            list.add(i, vo);
        }
        listObj.put("ReportVO", list);

        model.addAttribute("rlist", list);
    }

    @PostMapping(value = "/rptWrite")
    @ResponseBody
    public String postMethodName(@RequestBody ReportVO rvo) {
        log.info(">>>>>>>>>>>>>>>report 작성");
        rptSvc.write(rvo);
        return "index.html";
    }

}
