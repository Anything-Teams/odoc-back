package com.anything.odoc.project.vo;

import lombok.Data;

@Data
public class ProjectUserVO {
    private String userId;      /* 사용자아이디 */
    private String userPw;      /* 패스워드 */
    private String userState;   /* 회원상태 */
    private String isMotivationAlert;   /* 동기부여 알람여부 */
}