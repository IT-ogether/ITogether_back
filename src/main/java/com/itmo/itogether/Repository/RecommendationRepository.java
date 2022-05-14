package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.RecommendationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class RecommendationRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RecommendationDTO> findRecommendation(String field) {
        return jdbcTemplate.query("select A.information_id, A.information_title, A.logo \n" +
                "from information as A left join count_share as B \n" +
                "on A.information_id = B.information_id\n" +
                "order by ? desc\n" +
                "limit 3;\n", RecommendationMapper(), field);
    }

    private RowMapper<RecommendationDTO> RecommendationMapper() {
        return (rs, rowNum) -> {
            int informationId = rs.getInt("information_id");
            String title = rs.getString("information_title");
            String logo = rs.getString("logo");

            RecommendationDTO recommendationDTO = new RecommendationDTO(informationId, title, logo);

            return recommendationDTO;

        };
    }



}
