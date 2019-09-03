package net.nigne.yourtour.enrollment.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SellType {

    NEW("새 책"),
    USED("중고책");

    private final String description;
}
