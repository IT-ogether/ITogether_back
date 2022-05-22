package com.itmo.itogether.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostField {
    private Long memberId;
    private String field;
}
