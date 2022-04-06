package com.itmo.itogether.controller;

import com.itmo.itogether.domain.Information;
import com.itmo.itogether.service.InformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailInformationController {
    private InformationService informationService;

    public DetailInformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping(value = {"/{informationId}", "/home/{informationId}", "/main-info/{informationId}"})
    public Information findByInfoId(@PathVariable int informationId) {
        return informationService.findOneInformation(informationId);
    }
}
