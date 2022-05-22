package com.itmo.itogether.DTO;

public class PostMarkRequest {

    private static Long memberId;
    private static int informationId;

    public PostMarkRequest(Long memberId, int informationId){
        this.memberId=memberId;
        this.informationId=informationId;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public int getInformationId() {
        return this.informationId;
    }
}
