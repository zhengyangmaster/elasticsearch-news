package com.example.newssearch.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.example.newssearch.base.DataUtils;
import com.example.newssearch.common.reposity.NewsRepository;
import com.example.newssearch.common.utils.GeneratID;
import com.example.newssearch.dto.NewDto;
import com.example.newssearch.entity.News;
import com.example.newssearch.service.impl.NewServiceImpl;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/11
 */
@RestController
@RequestMapping("/searchNews")
public class SearchController {
    @Autowired
    private NewsRepository repository;

    @Autowired
    private NewServiceImpl service;


    @PostMapping("/news")

    public List<News> searchNews(@RequestBody String searchWord) {

        MatchQueryBuilder builder = QueryBuilders.matchQuery("content", searchWord);

        Iterable<News> search = repository.search(builder);
        List<News> newsList = new ArrayList<>();

        for (News news : search) {
            newsList.add(news);
        }

        return newsList;

    }

    @GetMapping("/getNews")

    public List<News> getByTitle(@RequestParam("searchWord") String searchWord) {

        return repository.findByTitle(searchWord);

    }

    @GetMapping("/getData")
    public String getData() {
        for (int i = 50; i <= 2000; i = i + 40) {
            JSONArray data = DataUtils.getData(i);
            List<News> list = JSONUtil.toList(data, News.class);
            for (News news : list) {
                news.setId(GeneratID.getGeneratID());
            }
            repository.saveAll(list);
        }
        return "添加成功！";
    }


    @PostMapping("/serchkeyword")
    public Page<News> searchWord(@RequestBody NewDto newDto) {

        return service.listByKeyWord(newDto);


    }

}
