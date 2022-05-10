package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.FavorFieldDTO;
import com.itmo.itogether.Domain.CountShare;
import com.itmo.itogether.Repository.CountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountService {

    private final CountRepository countRepository;

    public CountService(CountRepository countRepository) {
        this.countRepository = countRepository;
    }

    public Optional<CountShare> findCountByInformationId(int informationId) {

        return countRepository.findCountByInformationId(informationId);
    }

    public FavorFieldDTO findField(Long memberId) {

        return countRepository.findField(memberId);
    }

    public Integer updateOnlySumCount(CountShare countShare) {
        countRepository.updateOnlySumCount(countShare);

        return countShare.getSumCount();
    }

    public Integer updateCount(CountShare countShare) {
        countRepository.updateCount(countShare);

        return countShare.getSumCount();
    }

}
