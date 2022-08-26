package com.example.newssearch.service.impl;

import com.example.newssearch.common.reposity.NewsRepository;
import com.example.newssearch.dto.NewDto;
import com.example.newssearch.entity.News;
import com.example.newssearch.service.NewService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/12
 */
@Service
public class NewServiceImpl implements NewService {

    @Autowired
    NewsRepository repository;


    @Override
    public Page<News> listByKeyWord(NewDto newDto) {
        //主体查询对象
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        //模糊搜索对象
        BoolQueryBuilder keyBuilder = new BoolQueryBuilder();
        //分类查询对象
        BoolQueryBuilder orBuilder = new BoolQueryBuilder();

        //通过关键词获取相关的数据
        if (StringUtils.isNotBlank(newDto.getKeyWord())){
            keyBuilder.should(QueryBuilders.wildcardQuery("title","*"+newDto.getKeyWord()+"*"));
            keyBuilder.should(QueryBuilders.wildcardQuery("content","*"+newDto.getKeyWord()+"*"));
            queryBuilder.must(keyBuilder);

        }
        if (StringUtils.isNotBlank(newDto.getCategory())){
            orBuilder.should(QueryBuilders.matchQuery("category",newDto.getCategory()));
            queryBuilder.must(orBuilder);
        }

        PageRequest pageRequest = PageRequest.of(newDto.getStart(), newDto.getLimit(), Sort.Direction.DESC, "time");


        return repository.search(queryBuilder, pageRequest);
    }
}
