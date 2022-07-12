package com.example.newssearch.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/12
 */
@Data
public class NewDto {
    private String title;
    private String content;
    private Date time;
    private String category;
    private String keyWord;

    private Integer start;
    private Integer limit;
}
