package com.itmo.itogether.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Information {

    private int informationId; // 상세정보코드
    private String informationTitle;
    private String siteUrl;
    private String logo;
    private String recruitmentPeriod;
    private String activityPeriod;
    private String details;
    private int categoryId;
}
