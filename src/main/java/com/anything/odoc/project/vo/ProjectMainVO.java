package com.anything.odoc.project.vo;

import lombok.Data;

@Data
public class ProjectMainVO {
    private int odocSn;
    private String userId;
    private String odocNm;
    private String frstRegDt;
    private String lastOdocDt;
    private String delYn;
    private String endYn;
    private String odocType;

    private String odocFavYn;
    private String odocThemaType;

    private String odocMonth;
    private String progress;
    private int odocYn;

    private String year;
    private String month;

    private int streamSn;
    private int maxStreamSn;
    private int themaId;

    private String themaConfirmYn;
}
