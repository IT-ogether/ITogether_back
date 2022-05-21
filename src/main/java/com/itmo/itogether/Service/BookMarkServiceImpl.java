package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.DeleteRequest;
import com.itmo.itogether.DTO.GetMarkRequest;
import com.itmo.itogether.DTO.GetMarkResponse;
import com.itmo.itogether.DTO.PostMarkRequest;
import com.itmo.itogether.Repository.BookMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    BookMarkRepository repository;

    @Autowired
    public void bookMarkService(DataSource source) {
        repository=new BookMarkRepository(source);
    }

    public void postMark(PostMarkRequest dto){

        repository.postFavorite(dto);

    }

    @Override
    public List<GetMarkResponse> getMark(GetMarkRequest dto) {

        return repository.getBookInfo(dto);
    }

    public void deleteMark(DeleteRequest dto){
        repository.deleteFavorite(dto);
    }
}
