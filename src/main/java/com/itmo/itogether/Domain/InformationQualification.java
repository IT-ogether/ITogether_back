package com.itmo.itogether.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class InformationQualification {

    private int informationQualificationId;
    private int qualificationId2;
    private int informationId;

    private List<Qualification> qualification;

}
