package com.itmo.itogether.Service;

import com.itmo.itogether.Domain.Information;
import com.itmo.itogether.Repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {

    private final InformationRepository informationRepository;

    @Autowired
    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }


    public List<Information> findPopularInfo() {
        System.out.println(informationRepository.findPopularInformation());

        return informationRepository.findPopularInformation();
    }




}
