package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.InformationDTO;
import com.itmo.itogether.DTO.ReviewDTO;
import com.itmo.itogether.Service.ReviewCrawlingService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewCrawlingRepositoryImpl implements ReviewCrawlingRepository {
    private JdbcTemplate jdbcTemplate;
    private ReviewCrawlingService reviewCrawlingService;

    public ReviewCrawlingRepositoryImpl(JdbcTemplate jdbcTemplate, ReviewCrawlingService reviewCrawlingService) {
        this.jdbcTemplate = jdbcTemplate;
        this.reviewCrawlingService = reviewCrawlingService;
    }

    @Override
    //@PostConstruct
    public void insertReviews() throws IOException {
        List<InformationDTO> informationDTO = findAllInfo();
        List<ReviewDTO> reviewDTO = new ArrayList<>();

        reviewDTO.addAll(reviewCrawlingService.getNaverReviews(informationDTO));
        reviewDTO.addAll(reviewCrawlingService.getTistoryReviews(informationDTO));
        reviewDTO.addAll(reviewCrawlingService.getVelogReviews(informationDTO));


        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("review").usingGeneratedKeyColumns("review_id");

        for (ReviewDTO review : reviewDTO) {
            SqlParameterSource params = new BeanPropertySqlParameterSource(review);
            jdbcInsert.execute(params);
        }
        deleteRedundancy(); // 중복 제거
        updateIsCrawled(); // 크롤링을 진행한 information 체크 (이후 크롤링할 때 중복해서 크롤링하는 것을 방지하기 위함)

        System.out.println("크롤링 종료");
    }

    @Override
    public List<InformationDTO> findAllInfo() {
        return jdbcTemplate.query("SELECT information_id, information_title FROM information WHERE is_crawled = 0", allInfoRowMapper());
    }

    private RowMapper<InformationDTO> allInfoRowMapper() {
        return (rs, rowNum) -> {
            InformationDTO informationDTO = new InformationDTO();
            informationDTO.setInformationId(rs.getInt("information_id"));
            informationDTO.setInformationTitle(rs.getString("information_title"));

            return informationDTO;
        };
    }

    @Override
    public void deleteRedundancy() {
        jdbcTemplate.execute("DELETE FROM review WHERE review_id NOT IN ( SELECT review_id FROM ( SELECT review_id FROM review GROUP BY title) AS review_id)");
    }

    @Override
    public void updateIsCrawled() {
        jdbcTemplate.execute("UPDATE information SET is_crawled = 1");
    }
}
