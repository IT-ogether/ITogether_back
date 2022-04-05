package com.itmo.itogether.Repository;

import com.itmo.itogether.Domain.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InformationRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Information> findPopularInformation() {

        return jdbcTemplate.query("select information_id, information_title, logo " +
                "from information " +
                "where information_id = 11 or information_id = 21 or information_id = 37 or information_id = 45 or information_id = 59 or information_id = 63;", popularInformationRowMapper());
    }

    private RowMapper<Information> popularInformationRowMapper() {
        return (rs, rowNum) -> {
            Information information = new Information();
            information.setInformationId(rs.getInt("information_id"));
            information.setInformationTitle(rs.getString("information_title"));
            //information.setSiteUrl(rs.getString("site_url"));
            information.setLogo(rs.getString("logo"));
            //information.setRecruitmentPeriod(rs.getString("recruitment_period"));
            //information.setActivityPeriod((rs.getString("activity_period")));
            //information.setDetails(rs.getString("details"));
            //information.setCategoryId(rs.getInt("categoryId"));

            return information;
        };
    }
}
