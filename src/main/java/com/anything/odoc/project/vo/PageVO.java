package com.anything.odoc.project.vo;

import lombok.Data;

@Data
public class PageVO {

    private int page;
    private int pageSize;

    public int getOffset() {
        return (page - 1) * pageSize;
    }

}
