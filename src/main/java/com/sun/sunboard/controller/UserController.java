package com.sun.sunboard.controller;

import com.sun.sunboard.dto.UserDTO;
import com.sun.sunboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok().build();
    }
    /**
     * 로그인
     *
     * @param user 유저 정보
     * @return 로그인이 성공한 경우 HTTP 200 Created 상태코드와 함께 응답
     */
    @PostMapping("login")
    public ResponseEntity<Void> login(@Validated @RequestBody UserDTO user) {
        UserDTO userId = userService.login(user);
        return ResponseEntity.ok().build();
    }

}
