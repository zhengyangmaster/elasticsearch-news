package com.example.newssearch;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.example.newssearch.base.DataUtils;
import com.example.newssearch.common.config.RabbitmqConfig;
import com.example.newssearch.common.reposity.NewsRepository;
import com.example.newssearch.common.utils.GeneratID;
import com.example.newssearch.common.utils.GetMessage;
import com.example.newssearch.common.utils.InitDataMap;
import com.example.newssearch.dto.NewDto;
import com.example.newssearch.entity.News;
import com.example.newssearch.po.Article;
import com.example.newssearch.service.impl.NewServiceImpl;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = NewsSearchApplication.class)
@RunWith(SpringRunner.class)
public class NewsSearchApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private GetMessage message;

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
    public void searchTest() {
        NewDto dto = new NewDto();
        dto.setKeyWord("习近平");
        dto.setCategory("news");
        Page<News> news = service.listByKeyWord(dto);
        news.forEach(System.out::println);
        System.out.println(news);

    }


    @Test
    public void mqtest() {

        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "#", "我是消息我来了");

    }


    @Test
    public void setnews() {
        JSONArray data = DataUtils.getData(1);
        List<Article> list = JSONUtil.toList(data, Article.class);
        list.forEach(article -> {
            try {
                article.setContent("这是一段内容");
                Map<String, Object> map = InitDataMap.entityToMap(article);
                rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "#", map);
                System.out.println(map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        });
        System.out.println("发送完成");


    }
}
