package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.Domain.Information;
import com.itmo.itogether.Domain.Review;
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
    public List<MainInformationDTO> searchKeyword(String keyword, int pageNum, int perPageNum) {
        return informationRepository.searchKeyword(keyword, pageNum, perPageNum);
    }

    @Override
    public List<MainInformationDTO> findByField(String field, int pageNum, int perPageNum) {
        return informationRepository.findByField(field, pageNum, perPageNum);
    }

    @Override
    public int countInfo(int categoryId) {
        switch (categoryId) {
            case 1:
                return informationRepository.countClubInfo();
            case 2:
                return informationRepository.countEducationInfo();
            case 3:
                return informationRepository.countSeminarInfo();
            case 4:
                return informationRepository.countCertificateInfo();
            case 5:
                return informationRepository.countKdtInfo();
            case 6:
                return informationRepository.countContestInfo();
        }
        return 0;
    }

    @Override
    public int countKeywordInfo(String keyword) {
        return informationRepository.countKeywordInfo(keyword);
    }

    @Override
    public int countFieldInfo(String field) {
        return informationRepository.countFieldInfo(field);
    }
}
