package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.Domain.Information;
import com.itmo.itogether.Domain.Review;

import java.util.List;

public interface InformationService {
    List<Information> findPopularInfo();

    List<MainInformationDTO> findAllClub(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllEducation(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllSeminar(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllCertificate(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllKdt(int pageNum, int perPageNum);

    List<MainInformationDTO> findAllContest(int pageNum, int perPageNum);

    DetailInformationDTO findByInfoId(int informationId);

    List<Review> findReviewById(int informationId);

    List<MainInformationDTO> searchKeyword(String keyword, int pageNum, int perPageNum);

    List<MainInformationDTO> findByField(String field, int pageNum, int perPageNum);

    int countInfo(int categoryId);

    int countKeywordInfo(String keyword);

    int countFieldInfo(String field);
}
