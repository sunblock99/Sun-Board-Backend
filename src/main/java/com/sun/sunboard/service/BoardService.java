package com.sun.sunboard.service;

import com.sun.sunboard.dto.BoardDTO;
import com.sun.sunboard.dto.PageDTO;
import com.sun.sunboard.mapper.BoardMapper;
import com.sun.sunboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserMapper userMapper;
    private final BoardMapper boardMapper;

    public  List<BoardDTO> getBoardList(PageDTO pageDTO) {
        RowBounds rowBounds = pageDTO.getRowBounds();
        return boardMapper.getBoardList(rowBounds);
    }

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
