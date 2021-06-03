package com.example.demo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ProjectInVO {
    private int prj_in_no;
    private String email;
    private String authority;
    private Date prj_in_d8;
    private Date prj_out_d8;
    private int prj_no;

    public ProjectInVO() {
    }

    public ProjectInVO(String email, Date prj_in_d8, Date prj_out_d8) {
        this.email = email;
        this.prj_in_d8 = prj_in_d8;
        this.prj_out_d8 = prj_out_d8;
    }
}
