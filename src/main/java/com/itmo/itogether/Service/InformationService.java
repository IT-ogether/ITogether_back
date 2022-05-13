package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.Domain.Information;
import com.itmo.itogether.Domain.Review;

import java.util.List;

public interface InformationService {
    List<Information> findPopularInfo();

    List<MainInformationDTO> findAllClub();

    List<MainInformationDTO> findAllEducation();

    List<MainInformationDTO> findAllSeminar();

    List<MainInformationDTO> findAllCertificate();

    List<MainInformationDTO> findAllKdt();

    List<MainInformationDTO> findAllContest();

    DetailInformationDTO findByInfoId(int informationId);

    List<Review> findReviewById(int informationId);

    List<MainInformationDTO> searchKeyword(String keyword);
}