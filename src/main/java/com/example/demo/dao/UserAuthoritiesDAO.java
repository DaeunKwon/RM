package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import com.example.demo.domain.Authorities;
import com.example.demo.domain.UserVO;

public interface UserAuthoritiesDAO {

    public List<String> getUserRoles(String email);

    int insertAuthority(Authorities authorities);

    int deleteAllAuthority(String email);

    // @SuppressWarnings("rawtypes")
    // List<HashMap> getRoleUserList(UserVO user);

}
