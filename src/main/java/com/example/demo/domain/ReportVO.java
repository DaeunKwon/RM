package com.example.demo.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReportVO {
    private int rpt_no;
    private int prj_no;
    private char rmv_YN;
    private String rpt_writer;
    private Date rpt_write_d8;
    private String rpt_mod_writer;
    private Date rpt_mod_d8;

    public ReportVO() {
    }

}
