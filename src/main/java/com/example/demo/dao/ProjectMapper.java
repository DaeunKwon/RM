package com.example.demo.dao;

import com.example.demo.domain.ProjectVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProjectMapper {
    void write(ProjectVO pvo);

}