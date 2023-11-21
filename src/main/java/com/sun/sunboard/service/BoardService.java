package com.sun.sunboard.service;

import com.sun.sunboard.dto.BoardDTO;
import com.sun.sunboard.mapper.BoardMapper;
import com.sun.sunboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserMapper userMapper;
    private final BoardMapper boardMapper;

    public void addPost(String userId, BoardDTO boardDTO) {
        int userNo = userMapper.findByUserNo(userId);
        boardDTO.setUserNo(userNo);
        boardMapper.insertPost(boardDTO);
    }

    public void removePost(BoardDTO boardDTO) {
        boardMapper.deletePost(boardDTO.getPostId());
    }

    public void modifyPost(BoardDTO boardDTO) {
        boardMapper.updatePost(boardDTO);
    }
}
