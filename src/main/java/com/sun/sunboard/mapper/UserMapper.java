package com.sun.sunboard.mapper;

import com.sun.sunboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public int checkDuplicatedId(String id);

    public void createUser(UserDTO user);

    UserDTO findByUserId(UserDTO user);

    int checkDuplicatedIdPwd(@Param("id") String userId,@Param("password") String oldPassword);

    int updatePassword(@Param("id") String userId, @Param("password") String newPassword);
}