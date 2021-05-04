package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.Authorities;
import com.example.demo.domain.AuthorityVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AuthorityMapper {
    void write(AuthorityVO authvo);

    List<String> getUserRoles(String email);

    int insertAuthority(Authorities authorities);

    int deleteAllAuthority(String email);
}
