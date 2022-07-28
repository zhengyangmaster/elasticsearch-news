package com.example.newssearch.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.newssearch.base.DataUtils;
import com.example.newssearch.common.AjaxResult;
import com.example.newssearch.dto.ArticleDto;
import com.example.newssearch.po.Article;
import com.example.newssearch.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/28
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //将接口获取的数据存入数据库
    @GetMapping("/saveToDataBase")
    public AjaxResult saveData() {
        for (int i = 1, k = 1; i < 1000; i += 40, k++) {

            JSONArray data = DataUtils.getData(i);
            List<Article> list = JSONUtil.toList(data, Article.class);
            articleService.saveBatch(list);
            System.out.println("存入数据成功当前存入当前条数为" + 40 * k);

        }
        return AjaxResult.success("存入数据成功");

    }

    //获取数据库中的全部数据
    @PostMapping("/listArticle")
    public AjaxResult listArticle(@RequestBody ArticleDto articleDto) {
        IPage<Article> page = articleService.listArticle(articleDto);
        return AjaxResult.success(page);

    }


}
