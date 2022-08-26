package com.example.newssearch.common.reposity;

import com.example.newssearch.entity.News;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/11
 */
@Component
public interface NewsRepository extends ElasticsearchRepository<News,Long> {

    List<News> findByTitle(String title);
    List<News> findByContent(String content);
    List<News> findByTimeBetween(Date beginTime,Date endTime);

}
