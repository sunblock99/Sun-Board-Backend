package com.sun.sunboard.controller;

import com.sun.sunboard.common.SessionUtil;
import com.sun.sunboard.dto.BoardDTO;
import com.sun.sunboard.error.exception.NullSessionException;
import com.sun.sunboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 작성
     *
     * @param session 세션 정보
     * @param boardDTO 게시글
     * @return 생성 성공한 경우 HTTP 200 Created 상태코드와 함께 응답
     */
    @PostMapping("write")
    public ResponseEntity<Void> write(@RequestBody BoardDTO boardDTO, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else{
            boardService.addPost(userId, boardDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 게시글 삭제
     *
     * @param session 세션 정보
     * @param boardDTO 게시글
     * @return 삭제 성공한 경우 HTTP 200 상태코드와 함께 응답
     */
    @DeleteMapping("remove")
    public ResponseEntity<Void> remove(@RequestBody BoardDTO boardDTO, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else{
            boardService.removePost(boardDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
