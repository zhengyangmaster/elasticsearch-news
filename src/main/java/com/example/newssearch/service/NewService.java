package com.example.newssearch.service;

import com.example.newssearch.dto.NewDto;
import com.example.newssearch.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/12
 */
@Service
public interface NewService {

    public Page<News> listByKeyWord(NewDto newDto);
}
