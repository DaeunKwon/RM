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
}
