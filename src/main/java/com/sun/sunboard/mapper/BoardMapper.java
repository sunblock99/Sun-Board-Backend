package com.sun.sunboard.mapper;

import com.sun.sunboard.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertPost(BoardDTO boardDTO);

    void deletePost(int postId);

    void updatePost(BoardDTO boardDTO);

    List<BoardDTO> getBoardList(RowBounds rowBounds);

    BoardDTO seletePost(int postId);

    void updateHit(@Param("postId") int postId,@Param("hit") int viewCount);
}
