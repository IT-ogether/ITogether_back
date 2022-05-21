package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.DTO.ReviewDTO;
import com.itmo.itogether.Domain.*;
import com.itmo.itogether.Repository.InformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {

    private final InformationRepository informationRepository;

    public InformationServiceImpl(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @Override
    public List<Information> findPopularInfo() {
        System.out.println(informationRepository.findPopularInformation());

        return informationRepository.findPopularInformation();
    }

    @Override
    public List<MainInformationDTO> findAllClub() {
        return informationRepository.findAllClub();
    }

    @Override
    public List<MainInformationDTO> findAllEducation() {
        return informationRepository.findAllEducation();
    }

    @Override
    public List<MainInformationDTO> findAllSeminar() {
        return informationRepository.findAllSeminar();
    }

    @Override
    public List<MainInformationDTO> findAllCertificate() {
        return informationRepository.findAllCertificate();
    }

    @Override
    public List<MainInformationDTO> findAllKdt() {
        return informationRepository.findAllKdt();
    }

    @Override
    public List<MainInformationDTO> findAllContest() {
        return informationRepository.findAllContest();
    }

    @Override
    public DetailInformationDTO findByInfoId(int informationId) {
        return informationRepository.findByInfoId(informationId);
    }

    @Override
    public List<ReviewDTO> findReviewById(int informationId) {
        return informationRepository.findReviewById(informationId);
    }
}
