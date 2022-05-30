package com.itmo.itogether.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecommendationDTO {
    int informationId;
    String title;
    String logo;
    String categoryName;
}
