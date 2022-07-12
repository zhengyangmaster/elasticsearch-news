package com.example.newssearch;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.example.newssearch.base.DataUtils;
import com.example.newssearch.dto.NewDto;
import com.example.newssearch.entity.News;
import com.example.newssearch.reposity.NewsRepository;
import com.example.newssearch.service.NewService;
import com.example.newssearch.service.impl.NewServiceImpl;
import com.example.newssearch.utils.GeneratID;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = NewsSearchApplication.class)
@RunWith(SpringRunner.class)
public class NewsSearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private NewsRepository repository;

    @Autowired
    NewServiceImpl service;

    @Test
   public void contextLoads() {

    }


    @Test
   public void saveData(){

        JSONArray array = DataUtils.getData(40);
        List<News> list = JSONUtil.toList(array, News.class);
        for (News news : list) {
            news.setId(GeneratID.getGeneratID());
        }
        repository.saveAll(list);
        System.out.println(list.toArray().length);

    }

    @Test
    public void queryData(){

        MatchQueryBuilder query = QueryBuilders.matchQuery("content", "习近平");

        Iterable<News> search = repository.search(query);

        search.forEach(System.out::println);
    }

    @Test
    public void searchTest(){
        NewDto dto = new NewDto();
        dto.setKeyWord("习近平");
        dto.setCategory("news");
        Page<News> news = service.listByKeyWord(dto);
        news.forEach(System.out::println);
        System.out.println(news);

    }
}
