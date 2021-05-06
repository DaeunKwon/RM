package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.Authorities;

public interface UserAuthoritiesDAO {

    public List<String> getUserRoles(String email);

    void insertAuthority(Authorities authorities);

    int deleteAllAuthority(String email);

    // @SuppressWarnings("rawtypes")
    // List<HashMap> getRoleUserList(UserVO user);

}
