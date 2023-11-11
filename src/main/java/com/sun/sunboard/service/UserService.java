package com.sun.sunboard.service;


import com.sun.sunboard.common.SHA256Util;
import com.sun.sunboard.dto.UserDTO;
import com.sun.sunboard.error.exception.DuplicatedIdException;
import com.sun.sunboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

