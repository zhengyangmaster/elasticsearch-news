package com.example.newssearch.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZhuZhengYang
 * @since 2022/7/28
 */
public class PageEntity {
    private Long page;
    private Long pageSize;

    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public <T> Page<T> getPage() {
        return new Page<T>(Objects.nonNull(this.page) ? this.page : 1, Objects.nonNull(this.pageSize) ? this.pageSize : 20);
    }
}
