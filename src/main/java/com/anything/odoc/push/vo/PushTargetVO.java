package com.anything.odoc.push.vo;

import lombok.Data;

@Data
public class PushTargetVO {
    private String userId;
    private int odocSn;
    private String odocNm;
    private String pushToken;
    private String streamSn;
    private String odocNames;
}