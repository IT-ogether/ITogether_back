package com.itmo.itogether.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FavorFieldDTO {
    private List<String> fields;
}
