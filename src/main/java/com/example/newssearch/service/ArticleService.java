package com.example.newssearch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.newssearch.dto.ArticleDto;
import com.example.newssearch.po.Article;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/28
 */

public interface ArticleService extends IService<Article> {

    IPage<Article> listArticle(ArticleDto articleDto);
}
