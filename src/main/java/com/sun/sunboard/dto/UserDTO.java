package com.sun.sunboard.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    /**
     * 유저고유번호
     */
    private int userNo;
    /**
     * 아이디
     */
    @NotNull(message = "아이디는 필수입니다.")
    private String id;
    /**
     * 비밀번호
     */
    private String password;
    /**
     * 이름
     */
    private String name;
    /**
     * 생년월일
     */
    private int birthday;
    /**
     * 가입일
     */
    private LocalDateTime joinDate;
    /**
     * 마지막 로그인일자
     */
    private LocalDateTime lastLoginDate;
    /**
     * 상태
     * 0 : 비활성화
     * 1 : 활성화(디폴트)
     */
    private boolean status;
    /**
     * 가입구분
     * 0 : 일반회원
     * 1 : 카카오회원
     */
    private String signupType;

}

