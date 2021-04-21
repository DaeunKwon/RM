package com.example.demo.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReportVO {
    private int rpt_no;
    private String rpt_title;
    private String rpt_content;
    private String rpt_remark;
    private char rmv_YN;
    private Date rpt_write_d8;
    private Date rpt_mod_d8;
}
