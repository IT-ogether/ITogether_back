package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.InformationDTO;

import java.io.IOException;
import java.util.List;

public interface ReviewCrawlingRepository {
    void insertReviews() throws IOException;
    List<InformationDTO> findAllInfo();
}
