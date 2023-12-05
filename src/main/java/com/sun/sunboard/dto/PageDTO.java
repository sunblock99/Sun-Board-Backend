package com.sun.sunboard.dto;

import lombok.*;
import org.apache.ibatis.session.RowBounds;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class PageDTO {

    private int page;
    private int size;

    public RowBounds getRowBounds() {
        return new RowBounds((page - 1) * size, size);
    }

    public PageDTO() {
        this.page = 1;
        this.size = 10;
    }


    public void setPage(int page) {
        this.page = (page <= 0) ? 1 : page;
    }


    public void setSize(int size) {
        this.size = (size <= 0) ? 10 : size;
    }
}
