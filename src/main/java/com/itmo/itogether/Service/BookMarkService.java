package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.DeleteRequest;
import com.itmo.itogether.DTO.GetMarkRequest;
import com.itmo.itogether.DTO.GetMarkResponse;
import com.itmo.itogether.DTO.PostMarkRequest;

import java.sql.SQLException;
import java.util.List;


public interface BookMarkService {
    public void postMark(PostMarkRequest dto);
    public List<GetMarkResponse> getMark(GetMarkRequest dto) throws SQLException;
    public void deleteMark(DeleteRequest dto);
}
