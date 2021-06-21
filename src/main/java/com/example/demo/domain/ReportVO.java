package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReportVO {
    private int rpt_no;
    private int prj_no;
    private char rmv_YN;
    private int rpt_writer;
    private Date rpt_write_d8;
    private int rpt_mod_writer;
    private Date rpt_mod_d8;
    private String name;
    private String prj_title;
    private List<ReportDetailVO> detailList;

    public ReportVO() {
    }
}
