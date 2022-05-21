package com.itmo.itogether.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
    private int totalCount; // 게시판 전체 데이터 개수
    private int displayPageNum = 10; // 하단의 페이지 번호가 한번에 몇개 표시되는지
                                     // 만약 5라면 이전 1 2 3 4 5 다음
                                     // 다음 누르면 이전 6 7 8 9 10 다음 이런식

    private int startPage; // 화면의 시작 번호
    private int endPage;  // 화면의 끝 번호
    private boolean prev; // 페이징 이전 버튼 활성화 여부
    private boolean next; // 페이징 다음 버튼 활성화 여부

    private CriteriaDTO cri;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        pagingData();
    }

    private void pagingData() {

        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        // endPage = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 개수) * 화면에 보여질 페이지 번호의 개수
        startPage = (endPage - displayPageNum) + 1;
        // startPage = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 개수) + 1

        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        // 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의개수

        prev = startPage != 1;
        // 이전 버튼 생성 여부 = 시작 페이지 번호가 1과 같으면 false, 아니면 true
        next = endPage * cri.getPerPageNum() < totalCount;
        // 다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 개수가 총 게시글의 수보다
        // 크거나 같으면 false, 아니면 true
    }
}