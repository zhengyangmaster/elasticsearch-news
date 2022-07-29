package com.example.newssearch.utils;

import com.example.newssearch.exception.BizException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/29
 */
public class HttpServletUtil {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BizException("当前请求参数为空或数据缺失，请联系管理员");
        } else {
            return requestAttributes.getRequest();
        }
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BizException("当前请求参数为空或数据缺失，请联系管理员");
        } else {
            return requestAttributes.getResponse();
        }
    }


}
