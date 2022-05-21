package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.FavorFieldDTO;
import com.itmo.itogether.DTO.PostField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
@Slf4j
public class PreferenceFieldRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public FavorFieldDTO findField(Long memberId) {
        log.info("dto={}", favorFieldDTORowMapper());
        try {
            FavorFieldDTO favorFieldDTO = jdbcTemplate.queryForObject("select favor_field_name from member_field where member_id = ?", favorFieldDTORowMapper(), memberId);

            return favorFieldDTO;
        } catch(EmptyResultDataAccessException e) {
            FavorFieldDTO favorFieldDTO = new FavorFieldDTO(null);
            return favorFieldDTO;
        }



    }

    public void postField(PostField postField) {
        log.info("postMemberId={}", postField.getMemberId());
        jdbcTemplate.update("insert into member_field values(?,?);", postField.getMemberId(), postField.getField());
    }

    public void deleteField(PostField postField) {
        log.info("deleteMemberId={}", postField.getMemberId());
        jdbcTemplate.update("delete from member_field where member_id = ? and favor_field_name = ?;", postField.getMemberId(), postField.getField());
    }

    private RowMapper<FavorFieldDTO> favorFieldDTORowMapper() {
        return (rs, rowNum) -> {

            String field = rs.getString("favor_field_name");
            log.info("field={}", field);
            FavorFieldDTO favorFieldDTO = new FavorFieldDTO(field);

            return favorFieldDTO;
        };
    }
}
