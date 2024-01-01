package com.sun.sunboard.controller;

import com.sun.sunboard.common.SessionUtil;
import com.sun.sunboard.dto.BoardDTO;
import com.sun.sunboard.dto.CommentDTO;
import com.sun.sunboard.dto.PageDTO;
import com.sun.sunboard.error.exception.NullSessionException;
import com.sun.sunboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/board")
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
    @PostMapping("/write")
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
    @DeleteMapping("/remove")
    public ResponseEntity<Void> remove(@RequestBody BoardDTO boardDTO, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else{
            boardService.removePost(boardDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 게시글 수정
     *
     * @param session 세션 정보
     * @param boardDTO 게시글
     * @return 수정 성공한 경우 HTTP 200 상태코드와 함께 응답
     */
    @PutMapping("/modify")
    public ResponseEntity<Void> modify(@RequestBody BoardDTO boardDTO, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else{
            boardService.modifyPost(boardDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 게시글 목록 조회
     *
     * @return HTTP 200 상태코드와 함께 목록 응답
     */
    @GetMapping("/list")
    public ResponseEntity<List<BoardDTO>> list(@ModelAttribute PageDTO pageDTO) {
        List<BoardDTO> boardList = boardService.getBoardList(pageDTO);

        return ResponseEntity.ok(boardList);
    }

    /**
     * 게시글 상세 목록 조회
     *
     * @return HTTP 200 상태코드와 함께 게시글 응답
     */
    @GetMapping("/{postId}")
    public ResponseEntity<BoardDTO> detail(@PathVariable("postId") int postId) {
        BoardDTO board = boardService.getBoard(postId);
        int hit = boardService.incrementViewCount(postId);
        board.setHit(hit);
        return ResponseEntity.ok(board);
    }

    /**
     * 게시글 좋아요 기능
     *
     * @return HTTP 200 상태코드와 함께 게시글 응답
     */
    @GetMapping("/like/{postId}")
    public ResponseEntity<Void> like(@PathVariable("postId") int postId, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else {
            boardService.addLike(postId,userId);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 댓글 작성
     *
     * @param session 세션 정보
     * @param commentDTO 댓글
     * @return 생성 성공한 경우 HTTP 200 Created 상태코드와 함께 응답
     */
    @PostMapping("/write/comment")
    public ResponseEntity<Void> commentWrite(@RequestBody CommentDTO commentDTO, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else{
            boardService.addComment(commentDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 댓글 수정
     *
     * @param session 세션 정보
     * @param commentDTO 댓글
     * @return 수정 성공한 경우 HTTP 200 상태코드와 함께 응답
     */
    @PutMapping("/modify/comment")
    public ResponseEntity<Void> modifyComment(@RequestBody CommentDTO commentDTO, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else{
            boardService.modifyComment(commentDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 댓글 삭제
     *
     * @param session 세션 정보
     * @param boardDTO 게시글
     * @return 삭제 성공한 경우 HTTP 200 상태코드와 함께 응답
     */
    @DeleteMapping("/remove/comment/{commentId}")
    public ResponseEntity<Void> removeComment(@PathVariable("commentId") int commentId, HttpSession session) {
        String userId = SessionUtil.getLoginUserId(session);
        if(userId == null || userId.isEmpty()){
            throw new NullSessionException("세션이 만료되었습니다.");
        }else{
            boardService.removeComment(commentId);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 댓글 목록 조회
     *
     * @return HTTP 200 상태코드와 함께 목록 응답
     */
    @GetMapping("/list/comment/{postId}")
    public ResponseEntity<List<CommentDTO>> listComment(@PathVariable("postId") int postId) {
        List<CommentDTO> commentList = boardService.getCommentList(postId);

        return ResponseEntity.ok(commentList);
    }

}
