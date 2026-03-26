package com.anything.odoc.project.vo;

import lombok.Data;

@Data
public class ProjectMonthVO {
    private String odocNm;
    private int createdDay;
    private String checkedDates;
    private String checkedTimestamps;
    private double progress;
}