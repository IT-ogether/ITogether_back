package com.itmo.itogether.Service;

import com.itmo.itogether.DTO.FavorFieldDTO;
import com.itmo.itogether.DTO.PostField;
import com.itmo.itogether.Repository.PreferenceFieldRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PreferenceFieldService {
    private final PreferenceFieldRepository preferenceFieldRepository;

    public PreferenceFieldService(PreferenceFieldRepository preferenceFieldRepository) {
        this.preferenceFieldRepository = preferenceFieldRepository;
    }

    public FavorFieldDTO findField(Long memberId) {

        return preferenceFieldRepository.findField(memberId);
    }

    public void postField(PostField postField) {
        preferenceFieldRepository.postField(postField);
    }

    public void deleteField(PostField postField) {
        preferenceFieldRepository.deleteField(postField);
    }

}
