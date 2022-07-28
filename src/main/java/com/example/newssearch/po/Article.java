package com.example.newssearch.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author ZhuZhengYang
 * @since 2022/7/28
 */
@Data
public class Article {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    @TableField("pubTime")
    private Date time;
    private String src;
    private String category;
    private String pic;
    private String url;
    @TableField("webUrl")
    private String weburl;
    private String content;
}
