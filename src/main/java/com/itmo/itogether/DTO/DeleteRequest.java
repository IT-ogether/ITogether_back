package com.itmo.itogether.DTO;

public class DeleteRequest {
    private static Long memberId;
    private static int informationId;

    public DeleteRequest(Long memberId,int informationId){

        this.memberId=memberId;
        this.informationId=informationId;

    }

    public static Long getMemberId() {
        return memberId;
    }

    public static int getInformationId() {
        return informationId;
    }
}
