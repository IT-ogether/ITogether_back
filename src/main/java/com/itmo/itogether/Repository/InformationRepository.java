package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.Domain.Information;
import com.itmo.itogether.Domain.Review;

import java.util.List;

public interface InformationRepository {

    List<Information> findPopularInformation();

    DetailInformationDTO findByInfoId(int informationId);

    List<MainInformationDTO> findAllClub(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllEducation(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllSeminar(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllCertificate(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllKdt(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllContest(int pageNum, int perPageNum);

    List<Review> findReviewById(int informationId);

    List<MainInformationDTO> searchKeyword(String keyword);


    int countClubInfo();

    int countEducationInfo();

    int countSeminarInfo();

    int countCertificateInfo();

    int countKdtInfo();

    int countContestInfo();

}