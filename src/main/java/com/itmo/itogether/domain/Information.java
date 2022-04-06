package com.itmo.itogether.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Information {

    @JsonProperty("information_Id")
    private Long informationId; // 상세정보코드
    @JsonProperty("information_title")
    private String informationTitle;
    @JsonProperty("site_url")
    private String siteUrl;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("recruitment_period")
    private String recruitmentPeriod;
    @JsonProperty("activity_period")
    private String activityPeriod;
    @JsonIgnore
    private List<RecruitmentField> recruitmentField;
    @JsonIgnore
    private List<InformationQualification> informationQualification;
    @JsonProperty("qualifications")
    private List<String> qualifications;
    @JsonProperty("fields")
    private List<String> fields;
    @JsonProperty("details")
    private String details;
    @JsonIgnore
    private int categoryId;
}

