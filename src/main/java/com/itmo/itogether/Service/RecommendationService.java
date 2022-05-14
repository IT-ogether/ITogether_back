package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.RecommendationDTO;
import com.itmo.itogether.Repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public List<RecommendationDTO> findRecommendation(String field) {
        return recommendationRepository.findRecommendation(field);
    }
}
