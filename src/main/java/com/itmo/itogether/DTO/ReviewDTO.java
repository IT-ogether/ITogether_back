package com.itmo.itogether.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private Integer review_id;
    private int information_id;
    private String title;
    private String url;
    private String site;
}
