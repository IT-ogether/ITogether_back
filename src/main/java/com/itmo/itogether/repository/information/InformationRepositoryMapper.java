package com.itmo.itogether.repository.information;

import com.itmo.itogether.domain.Information;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InformationRepositoryMapper {
    Information findByInfoId(@Param("informationId") int informationId);

    List<Information> findAllClub();

    List<Information> findAllEducation();

    List<Information> findAllSeminar();

    List<Information> findAllCertificate();

    List<Information> findAllKdt();

    List<Information> findAllContest();
}
