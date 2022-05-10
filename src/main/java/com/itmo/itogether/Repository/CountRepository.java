package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.FavorFieldDTO;
import com.itmo.itogether.Domain.CountShare;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CountRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void updateOnlySumCount(CountShare countShare) {
        jdbcTemplate.update("update count_share set sum_count = ? where information_id = ?", countShare.getSumCount(), countShare.getInformationId());
    }

    public void updateCount(CountShare countShare) {
        if(countShare.getFrontend() != 0) {
            jdbcTemplate.update("update count_share set frontend = ? where information_id = ?",
                    countShare.getFrontend(), countShare.getInformationId());
        }
        if(countShare.getBackend() != 0) {
            jdbcTemplate.update("update count_share set backend = ? where information_id = ?",
                    countShare.getBackend(), countShare.getInformationId());
        }
        if(countShare.getIos() != 0) {
            jdbcTemplate.update("update count_share set ios = ? where information_id = ?",
                    countShare.getIos(), countShare.getInformationId());
        }
        if(countShare.getAndroid() != 0) {
            jdbcTemplate.update("update count_share set android = ? where information_id = ?",
                    countShare.getAndroid(), countShare.getInformationId());
        }
        if(countShare.getAi() != 0) {
            jdbcTemplate.update("update count_share set ai = ? where information_id = ?",
                    countShare.getAi(), countShare.getInformationId());
        }
        if(countShare.getCloud() != 0) {
            jdbcTemplate.update("update count_share set cloud = ? where information_id = ?",
                    countShare.getCloud(), countShare.getInformationId());
        }
        if(countShare.getSecurity() != 0) {
            jdbcTemplate.update("update count_share set security = ? where information_id = ?",
                    countShare.getSecurity(), countShare.getInformationId());
        }
        if(countShare.getBlockchain() != 0) {
            jdbcTemplate.update("update count_share set blockchain = ? where information_id = ?",
                    countShare.getBlockchain(), countShare.getInformationId());
        }

        jdbcTemplate.update("update count_share set sum_count = ? where information_id = ?", countShare.getSumCount(), countShare.getInformationId());
    }

    public Optional<CountShare> findCountByInformationId(int informationId) {

        List<CountShare> result = jdbcTemplate.query("select * from count_share where information_id = ?", countShareRowMapper(), informationId);

        return result.stream().findAny();

    }

    public FavorFieldDTO findField(Long memberId) {
        log.info("dto={}", favorFieldDTORowMapper());
        FavorFieldDTO favorFieldDTO = jdbcTemplate.queryForObject("select favor_field_name from member_field where member_id = ?", favorFieldDTORowMapper(), memberId);
        return favorFieldDTO;
    }

    private RowMapper<FavorFieldDTO> favorFieldDTORowMapper() {
        return (rs, rowNum) -> {

            String field = rs.getString("favor_field_name");
            log.info("field={}", field);
            FavorFieldDTO favorFieldDTO = new FavorFieldDTO(field);

            return favorFieldDTO;
        };
    }

    private RowMapper<CountShare> countShareRowMapper() {
        return (rs, rowNum) -> {
            CountShare countShare = new CountShare();
            countShare.setInformationId(rs.getInt("information_id"));
            countShare.setFrontend(rs.getInt("frontend"));
            countShare.setBackend(rs.getInt("backend"));
            countShare.setIos(rs.getInt("ios"));
            countShare.setAndroid(rs.getInt("android"));
            countShare.setAi(rs.getInt("ai"));
            countShare.setCloud(rs.getInt("cloud"));
            countShare.setSecurity(rs.getInt("security"));
            countShare.setBlockchain(rs.getInt("blockchain"));
            countShare.setSumCount(rs.getInt("sum_count"));

            return countShare;
        };
    }

}
