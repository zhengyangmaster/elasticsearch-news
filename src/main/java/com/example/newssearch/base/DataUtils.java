package com.example.newssearch.base;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/11
 */
public class DataUtils {

    public static JSONArray getData(Integer start) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appkey", "b460648b65b03b28a6f0dba7dbd4b46c");
        paramMap.put("channel", "头条");
        paramMap.put("num", 40);
        paramMap.put("start", start);
        String s = HttpUtil.post("http://open.liupai.net/news/get", paramMap);

        JSONObject data = JSONUtil.parseObj(s);

        JSONObject result = (JSONObject) data.get("result");

        return result.getJSONArray("list");


    }


}
