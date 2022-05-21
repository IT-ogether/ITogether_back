package com.itmo.itogether.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MainInformationDTO {
    private int informationId;
    private String title;
    private String logo;
    private String recruitmentPeriod;
    private List<String> fields;
}
