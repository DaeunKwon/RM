package com.example.demo.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReportVO {
    private int rpt_no;
    private int prj_no;
    private String rpt_title;
    private String rpt_content;
    private String rpt_remark;
    private char rmv_YN;
    private String rpt_writer;
    private Date rpt_write_d8;
    private String rpt_mod_writer;
    private Date rpt_mod_d8;

    public ReportVO() {
    }

    public ReportVO(String rpt_title, String rpt_content, String rpt_remark) {
        this.rpt_title = rpt_title;
        this.rpt_content = rpt_content;
        this.rpt_remark = rpt_remark;
    }
}
