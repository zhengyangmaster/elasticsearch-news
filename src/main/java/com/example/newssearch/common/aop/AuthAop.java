package com.example.newssearch.common.aop;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.newssearch.common.AuthConstant;
import com.example.newssearch.common.config.AllowHeadKeyConfig;
import com.example.newssearch.common.exception.BizException;
import com.example.newssearch.common.utils.HttpServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/7/29
 */
@Configuration
@Aspect
@Order(-100)
public class AuthAop implements AuthConstant {

    @Autowired
    private AllowHeadKeyConfig headKeyConfig;


    @Pointcut("@annotation(com.example.newssearch.common.annotation.Auth)")
    private void getAuthPointCut() {

    }

    @Before("getAuthPointCut()")
    public void doPermission(JoinPoint joinPoint) {
        HttpServletRequest request = HttpServletUtil.getRequest();
        if (!checkSign(request)) {
            throw new BizException("对不起，您没有权限访问该接口！");
        }
    }

    private boolean checkSign(HttpServletRequest request) {

        //判断请求头中的加密参数解密后，是否和请求头中原始数据一致
        String key = request.getHeader(APP_KEY_HEADER);
        String secret = request.getHeader(APP_SECRET_HEADER);

        //判断获取到的key和secret
        if (!checkAllowHead(key, secret)) {
            return false;
        }

        String time = request.getHeader(APP_T_HEADER);
        String s = request.getHeader(APP_SIGN_HEADER);
        String s1 = SecureUtil.md5(key + secret + time + "ZZY");
        boolean verify = Objects.equals(s1, s);
        // 如果验证通过，则判断时间，时间不允许超过60s
        if (verify) {
            DateTime date = DateUtil.date(Long.parseLong(time));
            DateTime now = DateUtil.date();
            return (DateUtil.between(now, date, DateUnit.SECOND) <= 60);
        }
        return false;


    }


    private boolean checkAllowHead(String key, String secret) {
        List<Map<String, String>> allowHeadKeys = headKeyConfig.getAllowHeadKeys();
        for (Map<String, String> allowHeadKey : allowHeadKeys) {
            String k = allowHeadKey.get(APP_KEY_HEADER);
            String s = allowHeadKey.get(APP_SECRET_HEADER);
            if (Objects.equals(key, k) && Objects.equals(s, secret)) {
                return true;
            }
        }
        return false;
    }
}
