package com.example.newssearch.common.utils;


import com.example.newssearch.po.Article;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ZhuZhengYang
 * @since 2022/8/15
 */
public class InitDataMap {

    private static final String[] keys = {"id", "title", "time", "src", "category", "pic", "url", "weburl", "content"};


    public static void initMap(List<Article> articles) {

        List<Article> collect = articles.stream().peek(article -> {
            try {
                Map<String, Object> map = entityToMap(article);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }).collect(Collectors.toList());


    }


    public static <T> Map<String, Object> entityToMap(T obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
