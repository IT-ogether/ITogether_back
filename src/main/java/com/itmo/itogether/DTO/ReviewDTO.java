package com.itmo.itogether.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@RequiredArgsConstructor
public class ReviewDTO {

    @Column("review_id")
    private int reviewId;
    @NonNull
    @Column("information_id")
    private int informationId;
    @NonNull
    private String title;
    @NonNull
    private String url;
    @NonNull
    private String site;
}
