package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.DetailInformationDTO;
import com.itmo.itogether.DTO.MainInformationDTO;
import com.itmo.itogether.DTO.ReviewDTO;
import com.itmo.itogether.Domain.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InformationRepositoryImpl implements InformationRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
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

    @Override
    public DetailInformationDTO findByInfoId(int informationId) {
        DetailInformationDTO detailInformationDTO = jdbcTemplate.queryForObject(
                ("SELECT I.information_id, I.information_title, I.site_url, I.logo, I.recruitment_period, " +
                        "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                        "where recruitment_field.information_id=I.information_id)= 0 then 'null' " +
                        "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                        "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as fields, " +
                        "(case when (select Count(information_qualification.information_id) from information_qualification " +
                        "where information_qualification.information_id=I.information_id)= 0 then 'null' " +
                        "else (select group_concat(qualification.qualification_name) from qualification inner join information_qualification " +
                        "where I.information_id=information_qualification.information_id and qualification.qualification_id=information_qualification.qualification_id2) end) as qualifications, " +
                        "I.details " +
                        "FROM information as I " +
                        "WHERE I.information_id = ?"), DetailInfoMapper(), informationId);
        return detailInformationDTO;
    }

    private RowMapper<DetailInformationDTO> DetailInfoMapper() {
        return (rs, rowNum) -> {
            String[] empty_field = new String[0];
            int informationId = rs.getInt("information_id");
            String title = rs.getString("information_title");
            String siteUrl = rs.getString("site_url");
            String logo = rs.getString("logo");
            String recruitmentPeriod = rs.getString("recruitment_period");
            List<String> field = List.of(rs.getString("fields").split(","));
            if (field.contains("null")) {
                field = List.of(empty_field);
            }
            List<String> qualification = List.of(rs.getString("qualifications").split(","));
            if (qualification.contains("null")) {
                qualification = List.of(empty_field);
            }
            String detail = rs.getString("details");

            DetailInformationDTO detailInformationDTO = new DetailInformationDTO(title, siteUrl, logo, recruitmentPeriod, field, qualification, detail);

            return detailInformationDTO;
        };
    }

    @Override
    public List<ReviewDTO> findReviewById(int informationId) {
        return jdbcTemplate.query(
                String.format("SELECT review.title, review.url, review.site FROM review WHERE review.information_id In (%s);", informationId), ReviewMapper());
    }

    private RowMapper<ReviewDTO> ReviewMapper() {
        return (rs, rowNum) -> {

            String title = rs.getString("title");
            String url = rs.getString("url");
            String site = rs.getString("site");

            ReviewDTO reviewDTO = new ReviewDTO(title, url, site);

            return reviewDTO;
        };
    }

    @Override
    public List<MainInformationDTO> findAllClub() {
        return jdbcTemplate.query(
                String.format("SELECT I.information_id, I.information_title, I.logo, I.recruitment_period, " +
                                      "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                                      "where recruitment_field.information_id=I.information_id)= 0 then 'null' " +
                                      "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                                      "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as field " +
                                      "FROM information as I " +
                                      "WHERE I.category_id = 1"), MainInfoMapper());
    }

    @Override
    public List<MainInformationDTO> findAllEducation() {
        return jdbcTemplate.query(
                String.format("SELECT I.information_id, I.information_title, I.logo, I.recruitment_period, " +
                                      "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                                      "where recruitment_field.information_id=I.information_id)= 0 then 'null' " +
                                      "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                                      "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as field " +
                                      "FROM information as I " +
                                      "WHERE I.category_id = 2"), MainInfoMapper());
    }

    @Override
    public List<MainInformationDTO> findAllSeminar() {
        return jdbcTemplate.query(
                String.format("SELECT I.information_id, I.information_title, I.logo, I.recruitment_period, " +
                                      "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                                      "where recruitment_field.information_id=I.information_id)= 0 then 'null' " +
                                      "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                                      "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as field " +
                                      "FROM information as I " +
                                      "WHERE I.category_id = 3"), MainInfoMapper());
    }

    @Override
    public List<MainInformationDTO> findAllCertificate() {
        return jdbcTemplate.query(
                String.format("SELECT I.information_id, I.information_title, I.logo, I.recruitment_period, " +
                                      "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                                      "where recruitment_field.information_id=I.information_id)= 0 then 'null' " +
                                      "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                                      "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as field " +
                                      "FROM information as I " +
                                      "WHERE I.category_id = 4"), MainInfoMapper());
    }

    @Override
    public List<MainInformationDTO> findAllKdt() {
        return jdbcTemplate.query(
                String.format("SELECT I.information_id, I.information_title, I.logo, I.recruitment_period, " +
                                      "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                                      "where recruitment_field.information_id=I.information_id)= 0 then 'null' " +
                                      "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                                      "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as field " +
                                      "FROM information as I " +
                                      "WHERE I.category_id = 5"), MainInfoMapper());
    }

    @Override
    public List<MainInformationDTO> findAllContest() {
        return jdbcTemplate.query(
                String.format("SELECT I.information_id, I.information_title, I.logo, I.recruitment_period, " +
                                      "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                                      "where recruitment_field.information_id=I.information_id)= 0 then 'null' " +
                                      "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                                      "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as field " +
                                      "FROM information as I " +
                                      "WHERE I.category_id = 6"), MainInfoMapper());
    }

    private RowMapper<MainInformationDTO> MainInfoMapper() {
        return (rs, rowNum) -> {
            String[] empty_field = new String[0];
            int informationId = rs.getInt("information_id");
            String title = rs.getString("information_title");
            String logo = rs.getString("logo");
            String recruitmentPeriod = rs.getString("recruitment_period");
            List<String> field = List.of(rs.getString("field").split(","));
            if (field.contains("null")) {
                field = List.of(empty_field);
            }

            MainInformationDTO mainInformationDTO = new MainInformationDTO(informationId, title, logo, recruitmentPeriod, field);

            return mainInformationDTO;
        };
    }
}
