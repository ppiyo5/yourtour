package net.nigne.yourtour.book.ui.dto;

import lombok.Data;

@Data
public class PageOptions {

    private int pageSize = defaultPageSize();

    private int pageNumber = 1;

    protected int defaultPageSize() {
        return 10;
    }
}
