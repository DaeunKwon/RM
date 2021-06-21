package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class ComVO {
    private Integer com_no;
    private String com_d8;
    private String com_start;
    private String com_end;
    private String com_total;
    private int prj_in_no;
    private int prj_no;
    private String email;
    private String name;
    private int monthC;
    private int monthS;
    private String day;

    public ComVO() {

    }

    public ComVO(String com_d8) {
        this.com_d8 = com_d8;
    }

    public ComVO(String com_d8, String com_start, String com_end) {
        this.com_d8 = com_d8;
        this.com_start = com_start;
        this.com_end = com_end;
    }

    @Builder
    public ComVO(Integer com_no, String com_d8, String com_start, String com_end, String com_total, int prj_in_no,
            String email, int prj_no, String name, int monthC, int monthS, String day) {
        this.com_no = com_no;
        this.com_d8 = com_d8;
        this.com_start = com_start;
        this.com_end = com_end;
        this.com_total = com_total;
        this.prj_in_no = prj_in_no;
        this.email = email;
        this.prj_no = prj_no;
        this.name = name;
        this.monthC = monthC;
        this.monthS = monthS;
        this.day = day;
    }

}
