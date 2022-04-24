package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.InformationDTO;
import com.itmo.itogether.DTO.ReviewDTO;

import java.io.IOException;
import java.util.List;

public interface ReviewCrawlingService {
    List<ReviewDTO> getNaverReviews(List<InformationDTO> informationDTO) throws IOException;

    List<ReviewDTO> getTistoryReviews(List<InformationDTO> informationDTO) throws IOException;

    List<ReviewDTO> getVelogReviews(List<InformationDTO> informationDTO) throws IOException;
}
