package com.example.newssearch.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "news.zzy")
public class AllowHeadKeyConfig {

    private List<Map<String, String>> allowHeadKeys = new ArrayList<>();


}

