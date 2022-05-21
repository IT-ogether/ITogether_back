package com.itmo.itogether.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DetailInformationDTO {
    private String title;
    private String siteUrl;
    private String logo;
    private String recruitmentPeriod;
    private List<String> fields;
    private List<String> qualification;
    private String details;
}
