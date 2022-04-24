package com.itmo.itogether.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    private String title;
    private String url;
    private String site;
}
