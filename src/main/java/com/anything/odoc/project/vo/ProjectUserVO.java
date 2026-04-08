package com.anything.odoc.project.vo;

import lombok.Data;

@Data
public class ProjectUserVO extends PageVO {
    private String userId;      /* 사용자아이디 */
    private String userPw;      /* 패스워드 */
    private String userState;   /* 회원상태 */
    private String isMotivationAlert;   /* 동기부여 알람여부 */
    private String adminYn;      /* 관리자여부 */
    private String frstRegDt;   /* 최초등록일시 */
    private String lastMdrDt;   /* 최종등록일시 */
    private String maxStreamSn;   /* 최대일수 */
    private String nickNm;    /* 닉네임 */
    private String rememberMe;  /* 자동로그인 */
}