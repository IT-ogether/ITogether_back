package com.itmo.itogether.DTO;

import java.util.List;

public class GetMarkResponse {

    private int informationId;
    private String title;
    private String logo;
    private String recruitmentPeriod;
    private List fields;

    public GetMarkResponse(int id,String title,String logo,String recruitment,List field){
        this.informationId=id;
        this.title=title;
        this.logo=logo;
        this.recruitmentPeriod=recruitment;
        this.fields=field;
    }

    public int getInformationId() {
        return informationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public String getRecruitmentPeriod() {
        return recruitmentPeriod;
    }

    public List getFields() {
        return fields;
    }
}
