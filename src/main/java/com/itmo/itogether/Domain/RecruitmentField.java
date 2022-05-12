package com.itmo.itogether.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RecruitmentField {

    private int recruitmentFieldId;
    private int fieldId;
    private int informationId;

    private List<Field> field;

}
