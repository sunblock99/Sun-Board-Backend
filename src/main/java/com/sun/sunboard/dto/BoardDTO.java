package com.sun.sunboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    /**
     * 게시글 번호
     */
    private int postId;
    /**
     * 게시자 번호
     */
    private int userNo;
    /**
     * 제목
     */
    private String title;
    /**
     * 내용
     */
    private String content;
    /**
     * 해시태그
     */
    private String hashtag;
    /**
     * 작성일자
     */
    private LocalDateTime uploadDate;
    /**
     * 수정일자
     */
    private LocalDateTime modifiedDate;
    /**
     * 상태
     */
    private boolean status;
    /**
     * 좋아요수
     */
    private int like;
    /**
     * 조회수
     */
    private int hit;

}
