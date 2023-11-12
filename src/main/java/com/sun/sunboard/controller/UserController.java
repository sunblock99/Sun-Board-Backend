package com.sun.sunboard.controller;

import com.sun.sunboard.common.SessionUtil;
import com.sun.sunboard.dto.UserDTO;
import com.sun.sunboard.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원 가입
     *
     * @param user 유저 정보
     * @return 회원 가입이 성공한 경우 HTTP 200 Created 상태코드와 함께 응답
     */
    @PostMapping("signup")
    public ResponseEntity<Void> signUp(@Validated @RequestBody UserDTO user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 로그인
     *
     * @param user 유저 정보
     * @return 로그인이 성공한 경우 HTTP 200 Created 상태코드와 함께 응답
     */
    @PostMapping("login")
    public ResponseEntity<Void> login(@Validated @RequestBody UserDTO user, HttpSession session) {
        if(user.getPassword() == null || user.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UserDTO loginUser = userService.login(user);
        if(loginUser == null){
            // ID or Password 실패시
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else if(loginUser != null){
            SessionUtil.setLoginUserId(session, loginUser.getUserNo());
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            throw new RuntimeException("Runtime Error ! !");
        }

    }

    /**
     * 로그아웃
     *
     * @param session 세션 정보
     *
     */
    @GetMapping("logout")
    public void logout(HttpSession session){
        SessionUtil.logoutUser(session);
    }


}
