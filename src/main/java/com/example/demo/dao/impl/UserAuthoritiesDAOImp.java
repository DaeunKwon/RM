package com.example.demo.dao.impl;

import java.util.List;

import com.example.demo.dao.UserAuthoritiesDAO;
import com.example.demo.domain.Authorities;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthoritiesDAOImp implements UserAuthoritiesDAO {
    private final Logger log = LoggerFactory.getLogger(UserAuthoritiesDAOImp.class);

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.auth";

    @Override
    public List<String> getUserRoles(String email) {
        return sql.selectList(ns + ".getUserAuthority", email);
    }

    @Override
    public void insertAuthority(Authorities authorities) {
        log.info(">>>>>>>auth: " + authorities.getRoles_authority());
        sql.insert(ns + ".insertAuthority", authorities);
    }

    @Override
    public int deleteAllAuthority(String email) {
        return sql.delete(ns + ".deleteAllAuthority", email);
    }

    // @SuppressWarnings("rawtypes")
    // @Override
    // public List<HashMap> getRoleUserList(UserVO user) {
    // List<String> roles = user.getRoles();
    // String role = roles.get(0);
    // String level = role.equals("ROLE_ROOT") ? "0" : "1";
    // return sql.selectList(ns + ".getRoleUserList", level);
    // }

}
