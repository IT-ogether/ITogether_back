package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.DTO.ReviewDTO;
import com.itmo.itogether.Domain.Information;

import java.util.List;

public interface InformationRepository {

    List<Information> findPopularInformation();

    DetailInformationDTO findByInfoId(int informationId);

    List<MainInformationDTO> findAllClub();

    List<MainInformationDTO> findAllEducation();

    List<MainInformationDTO> findAllSeminar();

    List<MainInformationDTO> findAllCertificate();

    List<MainInformationDTO> findAllKdt();

    List<MainInformationDTO> findAllContest();

    List<ReviewDTO> findReviewById(int informationId);
}
