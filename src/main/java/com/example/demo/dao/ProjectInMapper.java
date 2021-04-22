package com.example.demo.dao;

import com.example.demo.domain.ProjectInVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProjectInMapper {
    void write(ProjectInVO pINvo);
}
