package com.example.demo.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ProjectVO {
    private int prj_no;
    private String prj_title;
    private Date start_d8;
    private Date end_d8;
    private String prj_writer;
    private String prj_mod_writer;
    private Date prj_write_d8;
    private Date prj_mod_d8;
    private String prj_content;
    private String prj_remark;
    private String cond;
    private String rmv_YN;

    public ProjectVO() {
    }

    public ProjectVO(String prj_title, Date start_d8, Date end_d8, String prj_content, String prj_remark, String cond) {
        this.prj_title = prj_title;
        this.start_d8 = start_d8;
        this.end_d8 = end_d8;
        this.prj_content = prj_content;
        this.prj_remark = prj_remark;
        this.cond = cond;
    }
}
