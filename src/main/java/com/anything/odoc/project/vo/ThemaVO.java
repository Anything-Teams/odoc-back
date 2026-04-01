package com.anything.odoc.project.vo;

import lombok.Data;

@Data
public class ThemaVO {
    private int themaId;
    private String userId;      /* 사용자아이디 */
    private String themaNm;
    private String themaGetMethod;
    private int themaGetDay;
    private String themaGetCd;
}