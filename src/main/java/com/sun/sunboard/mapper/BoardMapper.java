package com.sun.sunboard.mapper;

import com.sun.sunboard.dto.BoardDTO;
import com.sun.sunboard.dto.CommentDTO;
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

    void insertLike(@Param("postId")int postId,@Param("userNo") int userNo);

    void insertComment(CommentDTO commentDTO);

    void updateComment(CommentDTO commentDTO);

    void deleteComment(int commentId);

    List<CommentDTO> selectCommentList(int postId);
}
