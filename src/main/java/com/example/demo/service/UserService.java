package com.example.demo.service;

import java.util.Map;

import com.example.demo.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public Map<String, Object> getUserInfo(int userSeq) {
        return userDAO.getUserInfo(userSeq);
    }
}
