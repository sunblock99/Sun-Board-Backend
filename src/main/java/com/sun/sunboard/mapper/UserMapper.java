package com.sun.sunboard.mapper;

import com.sun.sunboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int checkDuplicatedId(String id);

    public void createUser(UserDTO user);

}