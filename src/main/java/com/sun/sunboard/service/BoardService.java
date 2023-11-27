package com.sun.sunboard.service;

import com.sun.sunboard.dto.BoardDTO;
import com.sun.sunboard.dto.PageDTO;
import com.sun.sunboard.mapper.BoardMapper;
import com.sun.sunboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserMapper userMapper;
    private final BoardMapper boardMapper;
    private final RedisTemplate<String, String> redisTemplate;
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

    public BoardDTO getBoard(int postId) {
        BoardDTO board = boardMapper.seletePost(postId);
        return board;
    }

    public int incrementViewCount(int postId) {
        String key = "postId:" + postId + ":views";
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.increment(key);

        int viewCount = this.getViewCount(postId);
        if(viewCount % 100 == 0){
            boardMapper.updateHit(postId,viewCount);
        }
        return viewCount;
    }
    public int getViewCount(int postId){
        String key = "postId:" + postId + ":views";
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return Integer.parseInt(ops.get(key));
    }

    public void addLike(int postId, String userId) {
        int userNo = userMapper.findByUserNo(userId);
        boardMapper.insertLike(postId,userNo);
    }
}
