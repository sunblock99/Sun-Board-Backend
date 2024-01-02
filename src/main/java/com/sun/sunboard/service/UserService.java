package com.sun.sunboard.service;


import com.sun.sunboard.common.SHA256Util;
import com.sun.sunboard.dto.UserDTO;
import com.sun.sunboard.error.exception.DuplicatedIdException;
import com.sun.sunboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void createUser(UserDTO user) {
        if(checkDuplicatedId(user.getId())){
            throw new DuplicatedIdException("이미 사용중인 ID입니다.");
        }

        user.setPassword(SHA256Util.encryptSHA256(user.getPassword()));
        userMapper.createUser(user);
    }

    private boolean checkDuplicatedId(String id){
        return userMapper.checkDuplicatedId(id) == 1;

    }

    public UserDTO login(UserDTO user) {
        user.setPassword(SHA256Util.encryptSHA256(user.getPassword()));
        return userMapper.findByUserId(user);
    }

    public void passwordChange(String userId, String oldPassword, String newPassword) {
        SHA256Util.encryptSHA256(oldPassword);
        int checkDuplicatedIdPwd = userMapper.checkDuplicatedIdPwd(userId,oldPassword);
        if(checkDuplicatedIdPwd != 1){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다!");
        }
        SHA256Util.encryptSHA256(newPassword);
        int result = userMapper.updatePassword(userId, newPassword);
        if(result != 1){
            throw new RuntimeException("update Member Password ERROR!");
        }
    }
}

