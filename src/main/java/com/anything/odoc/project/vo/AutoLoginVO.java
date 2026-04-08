package com.anything.odoc.project.vo;

import java.time.LocalDateTime;

public class AutoLoginVO {
    private String userId;
    private String autoLoginToken;
    private LocalDateTime expireDt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAutoLoginToken() {
        return autoLoginToken;
    }

    public void setAutoLoginToken(String autoLoginToken) {
        this.autoLoginToken = autoLoginToken;
    }

    public LocalDateTime getExpireDt() {
        return expireDt;
    }

    public void setExpireDt(LocalDateTime expireDt) {
        this.expireDt = expireDt;
    }
}