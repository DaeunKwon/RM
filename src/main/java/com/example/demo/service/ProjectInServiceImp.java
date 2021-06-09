package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ProjectInDAO;
import com.example.demo.domain.ProjectInVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectInServiceImp implements ProjectInService {
    private final Logger log = LoggerFactory.getLogger(ProjectInServiceImp.class);

    @Autowired
    private ProjectInDAO pINdao;

    @Override
    public void add(ProjectInVO pINvo, int flag) {
        if (flag > 0) {
            pINdao.updateLeader(pINvo);
        } else {
            pINdao.insert(pINvo);
        }
    }

    @Override
    public List<ProjectInVO> getLedaerList() {
        return pINdao.getLeaderList();
    }

    @Override
    public ProjectInVO getLeaderInfo(int prj_no) {
        log.info(">>>>>>>>getleaderinfo service");
        return pINdao.getLeaderInfo(prj_no);
    }

    @Override
    public void addFollower(List<ProjectInVO> projectInList, int flag) {
        if (flag > 0) {
            pINdao.updateFollower(projectInList);
        } else {
            pINdao.addFollower(projectInList);
        }
    }

    @Override
    public List<ProjectInVO> getFollowerInfo(int prj_no) {
        return pINdao.getFollowerInfo(prj_no);
    }

    @Override
    public List<ProjectInVO> getUserProjectInfo(String email) {
        return pINdao.getUserProjectInfo(email);
    }

}
