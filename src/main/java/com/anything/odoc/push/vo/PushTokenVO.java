package com.anything.odoc.push.vo;

import lombok.Data;

@Data
public class PushTokenVO {
    private String userId;
    private String pushToken;
    private String deviceType;
    private String browserName;
    private String platform;
    private String userAgent;
    private String deviceName;
}