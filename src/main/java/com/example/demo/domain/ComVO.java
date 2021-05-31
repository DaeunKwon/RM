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
    private String prj_in_no;
    private String my_email;
    private String prj_no;

    public ComVO() {

    }

    public ComVO(String com_d8, String com_start, String com_end) {
        this.com_d8 = com_d8;
        this.com_start = com_start;
        this.com_end = com_end;
    }

    @Builder
    public ComVO(Integer com_no, String com_d8, String com_start, String com_end, String com_total, String prj_in_no,
            String my_email, String prj_no) {
        this.com_no = com_no;
        this.com_d8 = com_d8;
        this.com_start = com_start;
        this.com_end = com_end;
        this.com_total = com_total;
        this.prj_in_no = prj_in_no;
        this.my_email = my_email;
        this.prj_no = prj_no;
    }

}
