package com.itmo.itogether.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountShare {

    private int informationId;
    private int frontend;
    private int backend;
    private int ios;
    private int android;
    private int ai;
    private int cloud;
    private int security;
    private int blockchain;
    private int sumCount;
}
