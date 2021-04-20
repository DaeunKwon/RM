package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.domain.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserDAO udao;

    @Override
    public void join(UserVO uvo) {
        log.info(">>>>>>>>>>>>>>userservice join 진입");
        udao.join(uvo);
        log.info(">>>>>>>>>>>>>>userservice join 성공");
    }

    @Override
    public void modify(UserVO uvo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(String email) {
        // TODO Auto-generated method stub

    }

    @Override
    public int loginCheck(UserVO uvo) {
        return udao.loginCheck(uvo);
    }

}
