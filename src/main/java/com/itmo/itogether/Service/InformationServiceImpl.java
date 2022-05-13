package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
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
    public List<MainInformationDTO> findAllClub(int pageNum, int perPageNum) {
        return informationRepository.findAllClub(pageNum, perPageNum);
    }

    @Override
    public List<MainInformationDTO> findAllEducation(int pageNum, int perPageNum) {
        return informationRepository.findAllEducation(pageNum, perPageNum);
    }

    @Override
    public List<MainInformationDTO> findAllSeminar(int pageNum, int perPageNum) {
        return informationRepository.findAllSeminar(pageNum, perPageNum);
    }

    @Override
    public List<MainInformationDTO> findAllCertificate(int pageNum, int perPageNum) {
        return informationRepository.findAllCertificate(pageNum, perPageNum);
    }

    @Override
    public List<MainInformationDTO> findAllKdt(int pageNum, int perPageNum) {
        return informationRepository.findAllKdt(pageNum, perPageNum);
    }

    @Override
    public List<MainInformationDTO> findAllContest(int pageNum, int perPageNum) {
        return informationRepository.findAllContest(pageNum, perPageNum);
    }

    @Override
    public DetailInformationDTO findByInfoId(int informationId) {
        return informationRepository.findByInfoId(informationId);
    }

    @Override
    public List<Review> findReviewById(int informationId) {
        return informationRepository.findReviewById(informationId);
    }

    @Override
    public List<MainInformationDTO> searchKeyword(String keyword) {
        return informationRepository.searchKeyword(keyword);
    }

    @Override
    public int countInfo(int categoryId) {
        if (categoryId == 1) {
            return informationRepository.countClubInfo();
        } else if (categoryId == 2) {
            return informationRepository.countEducationInfo();
        } else if (categoryId == 3) {
            return informationRepository.countSeminarInfo();
        } else if (categoryId == 4) {
            return informationRepository.countCertificateInfo();
        } else if (categoryId == 5) {
            return informationRepository.countKdtInfo();
        } else if (categoryId == 6) {
            return informationRepository.countContestInfo();
        }
        return 0;
    }
}
