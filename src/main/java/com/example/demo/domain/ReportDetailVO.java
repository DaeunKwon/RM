package com.example.demo.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ReportDetailVO {
    private int rpt_detail_no;
    private int rpt_no;
    private Date rpt_start_time;
    private Date rpt_end_time;
    private String rpt_content;

}
