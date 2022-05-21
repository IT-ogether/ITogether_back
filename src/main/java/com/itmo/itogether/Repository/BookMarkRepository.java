package com.itmo.itogether.Repository;

import com.itmo.itogether.DTO.DeleteRequest;
import com.itmo.itogether.DTO.GetMarkRequest;
import com.itmo.itogether.DTO.GetMarkResponse;
import com.itmo.itogether.DTO.PostMarkRequest;
import com.itmo.itogether.Domain.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookMarkRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookMarkRepository(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public void postFavorite(PostMarkRequest dto){

        Favorite f = new Favorite();

        f.setMemberId(dto.getMemberId());
        f.setInformationId(dto.getInformationId());

        System.out.println(f.getInformationId()+f.getMemberId());
        jdbcTemplate.update("insert into favorite values(?,?);",f.getInformationId(),f.getMemberId());

    }

    public List<GetMarkResponse> getBookInfo(GetMarkRequest dto) {

        Long memberId=dto.getMemberId();
        System.out.println(memberId);

        List<Integer> id=jdbcTemplate.query("SELECT A.information_id " + "FROM favorite as A " +
                "WHERE A.member_id = ?;", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

                return rs.getInt("information_id");
            }
        }, memberId);

        for(int i=0;i<id.size();i++){
            System.out.println(id.get(i));
        }

        String param=String.join(",",id.stream().map(i->i+"").collect(Collectors.toList()));

        System.out.println(param);

        return jdbcTemplate.query(
                String.format("SELECT I.information_id, I.information_title, I.logo, I.recruitment_period, " +
                        "(case when (select Count(recruitment_field.information_id) from recruitment_field " +
                        "where recruitment_field.information_id=I.information_id)= 0 then ' ' " +
                        "else (select group_concat(field.field_name) from field inner join recruitment_field " +
                        "where I.information_id=recruitment_field.information_id and field.field_id=recruitment_field.field_id) end) as field " +
                        "FROM information as I " +
                        "WHERE I.information_id In (%s);",param),getBookInfoMapper());

    }

    private RowMapper<GetMarkResponse> getBookInfoMapper() {
        return (rs, rowNum)->{
            List<String> field;
            String[] empty_field=new String[0];


            int info_id=rs.getInt("information_id");
            System.out.println(info_id);
            String title=rs.getString("information_title");
            String logo=rs.getString("logo");
            String recruitment=rs.getString("recruitment_period");
            if(rs.getString("field").isBlank()) {
                field= List.of(empty_field);
            }else{
                field = List.of(rs.getString("field").split(","));
            }
            GetMarkResponse getMarkResponse=new GetMarkResponse(info_id,title,logo,recruitment,field);

            return getMarkResponse;
        };
    }

    public void deleteFavorite(DeleteRequest dto){

        Favorite f =new Favorite();

        f.setMemberId(dto.getMemberId());
        f.setInformationId(dto.getInformationId());

        jdbcTemplate.update("DELETE FROM favorite WHERE member_id=? AND information_id=?;",f.getMemberId(),f.getInformationId());

    }
}
