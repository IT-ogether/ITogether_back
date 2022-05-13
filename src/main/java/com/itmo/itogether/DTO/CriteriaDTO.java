package com.itmo.itogether.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CriteriaDTO {
    // 특정 페이지 조회를 위한 클래스
    private int page; // 현재 페이지 번호
    private int perPageNum; // 각 페이지당 보여줄 게시글의 개수
}
