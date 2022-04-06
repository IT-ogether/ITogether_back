package com.itmo.itogether.controller;

import com.itmo.itogether.domain.Information;
import com.itmo.itogether.service.InformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main-info")
public class InformationController {

    private InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping(value = {"/club/{informationId}", "/education/{informationId}", "/seminar/{informationId}",
            "/certificate/{informationId}", "/kdt/{informationId}", "/contest/{informationId}",})
    public Information findByInfoId(@PathVariable int informationId) {
        return informationService.findOneInformation(informationId);
    }

    @GetMapping(value = {"/", "/club"})
    public List<Information> findAllClub() {
        return informationService.findClub();
    }

    @GetMapping("/education")
    public List<Information> findAllEducation() {
        return informationService.findEducation();
    }

    @GetMapping("/seminar")
    public List<Information> findAllSeminar() {
        return informationService.findSeminar();
    }

    @GetMapping("/certificate")
    public List<Information> findAllCertificate() {
        return informationService.findCertificate();
    }

    @GetMapping("/kdt")
    public List<Information> findAllKdt() {
        return informationService.findKdt();
    }

    @GetMapping("/contest")
    public List<Information> findAllContest() {
        return informationService.findContest();
    }
}
