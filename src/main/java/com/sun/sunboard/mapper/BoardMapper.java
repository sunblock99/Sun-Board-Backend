package com.sun.sunboard.mapper;

import com.sun.sunboard.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void insertPost(BoardDTO boardDTO);

    void deletePost(int postId);

    void updatePost(BoardDTO boardDTO);
}
