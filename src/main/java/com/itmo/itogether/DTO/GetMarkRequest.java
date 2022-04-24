package com.itmo.itogether.DTO;

public class GetMarkRequest {

    private static Long memberId;

    public GetMarkRequest(Long memberId) {
        this.memberId = memberId;
    }

    public static Long getMemberId() {
        return memberId;
    }
}
