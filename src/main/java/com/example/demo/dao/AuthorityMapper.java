package com.example.demo.dao;

import com.example.demo.domain.AuthorityVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AuthorityMapper {
    void write(AuthorityVO authvo);
}
