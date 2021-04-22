package com.example.demo.dao;

import com.example.demo.domain.ReportVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReportMapper {
    void write(ReportVO rvo);
}
