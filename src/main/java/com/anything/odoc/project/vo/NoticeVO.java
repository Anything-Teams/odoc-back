package com.anything.odoc.project.vo;

import lombok.Data;

@Data
public class NoticeVO {
    private String noticeSn;
    private String noticeTitle;
    private String noticeContent;
    private String noticeType;
    private String noticeYn;
    private String beforeNoticeYn;
    private String delYn;

    private String userId;
}