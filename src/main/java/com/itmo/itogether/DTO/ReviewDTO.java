package com.itmo.itogether.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReviewDTO {
    private int reviewId;
    @NonNull
    private int informationId;
    @NonNull
    private String title;
    @NonNull
    private String url;
    @NonNull
    private String site;
}