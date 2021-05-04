package com.example.demo.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.example.demo.dao.UserAuthoritiesDAO;
import com.example.demo.domain.Authorities;
import com.example.demo.domain.UserVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthoritiesDAOImp implements UserAuthoritiesDAO {

    @Autowired
    private SqlSessionTemplate sql;

    private String ns = "com.example.demo.mapper.dao.AuthorityMapper";

    @Override
    public List<String> getUserRoles(String email) {
        return sql.selectList(ns + ".getUserAuthority", email);
    }

    @Override
    public int insertAuthority(Authorities authorities) {
        return sql.insert(ns + ".insertAuthority", authorities);
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
