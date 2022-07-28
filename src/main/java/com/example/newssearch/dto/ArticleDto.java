package com.example.newssearch.dto;

import com.example.newssearch.common.PageEntity;
import lombok.Data;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/28
 */
@Data
public class ArticleDto extends PageEntity {

    private String title;
    private String category;

}
