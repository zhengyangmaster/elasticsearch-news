package com.example.newssearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product")
public class News {

    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String src;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String category;
    @Field(type = FieldType.Keyword)
    private String pic;
    @Field(index = false, type = FieldType.Keyword)
    private String url;
    @Field(index = false, type = FieldType.Keyword)
    private String weburl;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

}
