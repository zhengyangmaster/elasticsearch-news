package com.example.newssearch.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.newssearch.dto.ArticleDto;
import com.example.newssearch.mapper.ArticleMapper;
import com.example.newssearch.po.Article;
import com.example.newssearch.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author ZhuZhengYang
 * @since 2022/7/28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public IPage<Article> listArticle(ArticleDto articleDto) {
        LambdaQueryWrapper<Article> query = Wrappers.lambdaQuery();
        query.like(ObjectUtil.isNotEmpty(articleDto.getTitle()), Article::getTitle, articleDto.getTitle())
                .like(ObjectUtil.isNotEmpty(articleDto.getCategory()), Article::getCategory, articleDto.getCategory());
        return this.page(articleDto.getPage(), query);
    }
}
