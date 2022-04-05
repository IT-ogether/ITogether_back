package com.itmo.itogether.Domain;


public class Information {

    private Integer informationId;
    private String informationTitle;
    private String siteUrl;
    private String logo;
    private String recruitmentPeriod;
    private String activityPeriod;
    private String details;
    private Integer categoryId;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getInformationTitle() {
        return informationTitle;
    }

    public void setInformationTitle(String informationTitle) {
        this.informationTitle = informationTitle;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRecruitmentPeriod() {
        return recruitmentPeriod;
    }

    public void setRecruitmentPeriod(String recruitmentPeriod) {
        this.recruitmentPeriod = recruitmentPeriod;
    }

    public String getActivityPeriod() {
        return activityPeriod;
    }

    public void setActivityPeriod(String activityPeriod) {
        this.activityPeriod = activityPeriod;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
